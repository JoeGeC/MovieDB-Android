package com.joe.tvDetails.repository.boundary

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.tvDetails.repository.response.TvDetailsResponse

interface TvDetailsLocal {
    suspend fun getTvDetails(tvId: Int): Either<TvDetailsResponse?, ErrorResponse?>

    suspend fun insert(response: TvDetailsResponse)
}