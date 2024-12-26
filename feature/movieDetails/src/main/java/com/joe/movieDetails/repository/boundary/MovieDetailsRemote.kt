package com.joe.movieDetails.repository.boundary

import com.joe.data.response.ErrorResponse
import com.joe.movieDetails.repository.response.MovieDetailsResponse
import com.joe.data.response.Result

interface MovieDetailsRemote {
    suspend fun getMovieDetails(movieId: Long): Result<MovieDetailsResponse?, ErrorResponse?>
}