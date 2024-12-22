package com.example.moviedetails.domain.usecase

import com.joe.core.Either
import com.joe.core.ErrorEntity
import com.joe.feature.domain.boundary.MovieDetailsRepository
import com.joe.feature.domain.entity.MovieDetails

class MovieDetailsUseCase(
    private val repository: MovieDetailsRepository
) {
    suspend fun getMovieDetails(movieId: Long): Either<MovieDetails?, ErrorEntity?> =
        repository.getMovieDetails(movieId)
}