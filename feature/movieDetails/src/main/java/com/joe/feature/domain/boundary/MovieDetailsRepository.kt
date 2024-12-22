package com.joe.feature.domain.boundary

import com.joe.core.Either
import com.joe.core.ErrorEntity
import com.joe.feature.domain.entity.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Long) : Either<MovieDetails?, ErrorEntity?>
}
