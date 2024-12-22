package com.joe.feature.repository.boundary

import com.joe.data.response.ErrorResponse
import com.joe.feature.repository.response.MovieDetailsResponse
import com.joe.data.response.Result

interface MovieDetailsRemote {
    suspend fun getMovieDetails(movieId: Long): Result<MovieDetailsResponse?, ErrorResponse?>
}