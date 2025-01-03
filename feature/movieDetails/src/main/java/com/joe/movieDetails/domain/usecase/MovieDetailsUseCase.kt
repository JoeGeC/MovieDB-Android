package com.joe.movieDetails.domain.usecase

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.movieDetails.domain.boundary.MovieDetailsRepository
import com.joe.core.entity.MediaDetailsEntity
import kotlinx.coroutines.flow.Flow

class MovieDetailsUseCase(
    private val repository: MovieDetailsRepository
) {
    suspend fun getMovieDetails(movieId: Int): Either<MediaDetailsEntity?, ErrorEntity?> =
        repository.getMovieDetails(movieId)
}