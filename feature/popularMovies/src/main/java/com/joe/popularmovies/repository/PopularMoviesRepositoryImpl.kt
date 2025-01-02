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

class PopularMoviesRepositoryImpl(
    private val remote: PopularMoviesRemote,
    private val local: PopularMoviesLocal,
) : PopularMoviesRepository {

    override suspend fun getPopularMovies(page: Int): Flow<Either<PopularMoviesEntity?, ErrorEntity?>> =
        flow {
            val localResult = local.getPopularMovies(page)
            if(localResult is Either.Success && !localResult.value.movies.isNullOrEmpty()){
                try {
                    emit(Either.Success(localResult.value.toEntity()))
                } catch (_: Exception){ }
            }
            val remoteResult = remote.getPopularMovies(page)
            remoteResult.fold(
                onSuccess = {
                    local.insertPopularMovies(it)
                    try {
                        emit(Either.Success(it.toEntity()))
                    } catch (e: Exception){
                        emit(Either.Failure(ErrorEntity(e.message ?: "")))
                    }
                },
                onFailure = { emit(Either.Failure(ErrorEntity(it.statusMessage))) }
            )
        }

}