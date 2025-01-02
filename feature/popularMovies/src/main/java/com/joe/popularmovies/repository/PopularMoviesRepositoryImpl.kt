package com.joe.popularmovies.repository

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popularmovies.domain.boundary.PopularMoviesRepository
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.local.converter.toEntity
import com.joe.popularmovies.local.converter.toLocal
import com.joe.popularmovies.repository.boundary.PopularMoviesLocal
import com.joe.popularmovies.repository.boundary.PopularMoviesRemote
import com.joe.popularmovies.repository.converter.toEntity

class PopularMoviesRepositoryImpl(
    private val remote: PopularMoviesRemote,
    private val local: PopularMoviesLocal,
) : PopularMoviesRepository {

    override suspend fun getPopularMovies(page: Int): Either<PopularMoviesEntity?, ErrorEntity?> =
        fetchLocal(page).fold(
            onSuccess = { Either.Success(it) },
            onFailure = { fetchRemote(page) }
        )

    private fun fetchLocal(page: Int): Either<PopularMoviesEntity?, ErrorEntity?> {
        val localResult = local.get(page)
        return if (localResult is Either.Success && !localResult.value?.movies.isNullOrEmpty()) {
            Either.Success(localResult.value.toEntity())
        } else Either.Failure(ErrorEntity("No data in local"))
    }

    private fun fetchRemote(page: Int): Either<PopularMoviesEntity?, ErrorEntity?> {
        val remoteResult = remote.getPopularMovies(page)
        remoteResult.fold(
            onSuccess = { remoteData ->
                local.insert(remoteData?.toLocal())
                return Either.Success(remoteData?.toEntity())
            },
            onFailure = { error ->
                return Either.Failure(ErrorEntity(error?.statusMessage ?: ""))
            }
        )
    }

}