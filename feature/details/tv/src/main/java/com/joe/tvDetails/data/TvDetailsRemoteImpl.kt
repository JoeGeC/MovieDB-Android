package com.joe.tvDetails.data

import com.joe.base.data.DetailsRemote
import com.joe.core.entity.Either
import com.joe.data.BaseRemote
import com.joe.data.json.NetworkProvider.API_KEY
import com.joe.data.response.ErrorResponse
import com.joe.tvDetails.data.response.TvDetailsResponse
import retrofit2.Retrofit

class TvDetailsRemoteImpl(retrofit: Retrofit) : BaseRemote(), DetailsRemote<TvDetailsResponse> {
    private val service: TvDetailsService = retrofit.create(
        TvDetailsService::class.java
    )

    override fun getDetails(mediaId: Int): Either<TvDetailsResponse?, ErrorResponse?> =
        tryRemote {
            service.getTvDetails(mediaId, API_KEY)
        }

}