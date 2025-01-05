package com.joe.movieDetails.domain.usecase

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.entity.MediaDetailsEntity
import com.joe.movieDetails.domain.boundary.MovieDetailsRepository

class MovieDetailsUseCase(
    private val repository: MovieDetailsRepository
) {
    suspend fun getMovieDetails(movieId: Int): Either<MediaDetailsEntity?, ErrorEntity?> =
        repository.getMovieDetails(movieId)
}