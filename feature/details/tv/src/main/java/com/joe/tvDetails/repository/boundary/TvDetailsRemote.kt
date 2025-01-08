package com.joe.tvDetails.repository.boundary

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.tvDetails.repository.response.TvDetailsResponse

interface TvDetailsRemote {
    suspend fun getMovieDetails(movieId: Int): Either<TvDetailsResponse?, ErrorResponse?>
}