package com.joe.moviesCast.data

import com.joe.cast.data.CastRemote
import com.joe.cast.data.response.CastListResponse
import com.joe.core.entity.Either
import com.joe.data.BaseRemote
import com.joe.data.json.NetworkProvider.API_KEY
import com.joe.data.response.ErrorResponse
import retrofit2.Retrofit

class MovieCastRemoteImpl(retrofit: Retrofit) : BaseRemote(), CastRemote {
    private val service: MovieCastService = retrofit.create(
        MovieCastService::class.java
    )

    override fun getCastOf(mediaId: Int): Either<CastListResponse?, ErrorResponse?> =
        tryRemote {
            service.getMovieCast(mediaId, API_KEY)
        }

}