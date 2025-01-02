package com.joe.popularmovies.repository

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popularmovies.domain.boundary.PopularMoviesRepository
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.repository.boundary.PopularMoviesLocal
import com.joe.popularmovies.repository.boundary.PopularMoviesRemote
import com.joe.popularmovies.repository.converter.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.joe.data.utils.emitSafeOrFailure
import com.joe.data.utils.emitSafeOrNone
import kotlinx.coroutines.flow.FlowCollector

class PopularMoviesRepositoryImpl(
    private val remote: PopularMoviesRemote,
    private val local: PopularMoviesLocal,
) : PopularMoviesRepository {

    override suspend fun getPopularMovies(page: Int): Flow<Either<PopularMoviesEntity?, ErrorEntity?>> = flow {
        fetchLocal(page)
        fetchRemote(page)
    }

    private suspend fun FlowCollector<Either<PopularMoviesEntity?, ErrorEntity?>>.fetchLocal(page: Int) {
        val localResult = local.getPopularMovies(page)
        if (localResult is Either.Success && !localResult.value.movies.isNullOrEmpty()) {
            emitSafeOrNone { localResult.value.toEntity() }
        }
    }

    private suspend fun FlowCollector<Either<PopularMoviesEntity?, ErrorEntity?>>.fetchRemote(page: Int) {
        val remoteResult = remote.getPopularMovies(page)
        remoteResult.fold(
            onSuccess = { remoteData ->
                local.insertPopularMovies(remoteData)
                emitSafeOrFailure { remoteData.toEntity() }
            },
            onFailure = { error ->
                emit(Either.Failure(ErrorEntity(error.statusMessage)))
            }
        )
    }

}