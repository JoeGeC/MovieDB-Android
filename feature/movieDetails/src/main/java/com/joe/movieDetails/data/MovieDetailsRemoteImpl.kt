package com.joe.movieDetails.data

import com.joe.data.BaseRemote
import com.joe.data.NetworkProvider
import com.joe.data.NetworkProvider.API_KEY
import com.joe.data.response.ErrorResponse
import com.joe.movieDetails.repository.boundary.MovieDetailsRemote
import com.joe.movieDetails.repository.response.MovieDetailsResponse
import com.joe.data.response.Result
import retrofit2.Retrofit

class MovieDetailsRemoteImpl(
    retrofit: Retrofit = NetworkProvider.createRetrofit()
) : BaseRemote(), MovieDetailsRemote {
    private val service: MovieDetailsService = retrofit.create(
        MovieDetailsService::class.java
    )

    override suspend fun getMovieDetails(movieId: Long): Result<MovieDetailsResponse?, ErrorResponse?> =
        tryRemote {
            service.getMovieDetails(movieId, API_KEY)
        }
}