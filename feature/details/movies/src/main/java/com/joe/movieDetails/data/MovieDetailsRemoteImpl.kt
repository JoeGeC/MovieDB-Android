package com.joe.movieDetails.data

import com.joe.base.data.DetailsRemote
import com.joe.core.entity.Either
import com.joe.data.BaseRemote
import com.joe.data.json.NetworkProvider.API_KEY
import com.joe.data.response.ErrorResponse
import com.joe.movieDetails.data.response.MovieDetailsResponse
import retrofit2.Retrofit

class MovieDetailsRemoteImpl(retrofit: Retrofit) : BaseRemote(), DetailsRemote<MovieDetailsResponse> {
    private val service: MovieDetailsService = retrofit.create(
        MovieDetailsService::class.java
    )

    override fun getDetails(mediaId: Int): Either<MovieDetailsResponse?, ErrorResponse?> =
        tryRemote {
            service.getMovieDetails(mediaId, API_KEY)
        }

}