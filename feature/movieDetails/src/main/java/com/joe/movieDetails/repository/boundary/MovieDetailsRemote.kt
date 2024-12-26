package com.joe.movieDetails.repository.boundary

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.movieDetails.repository.response.MovieDetailsResponse

interface MovieDetailsRemote {
    suspend fun getMovieDetails(movieId: Int): Either<MovieDetailsResponse?, ErrorResponse?>
}