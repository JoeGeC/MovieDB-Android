package com.joe.feature.domain.usecase

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.feature.domain.boundary.MovieDetailsRepository
import com.joe.feature.domain.entity.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

class MovieDetailsUseCase(
    private val repository: MovieDetailsRepository
) {
    suspend fun getMovieDetails(movieId: Long): Flow<Either<MovieDetailsEntity?, ErrorEntity?>> =
        repository.getMovieDetails(movieId)
}