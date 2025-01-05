package com.joe.movieDetails.data

import com.joe.core.entity.Either
import com.joe.data.BaseRemote
import com.joe.data.json.NetworkProvider.API_KEY
import com.joe.data.response.ErrorResponse
import com.joe.movieDetails.repository.boundary.MovieDetailsRemote
import com.joe.movieDetails.repository.response.MovieDetailsResponse
import retrofit2.Retrofit

class MovieDetailsRemoteImpl(retrofit: Retrofit) : BaseRemote(), MovieDetailsRemote {
    private val service: MovieDetailsService = retrofit.create(
        MovieDetailsService::class.java
    )

    override suspend fun getMovieDetails(movieId: Int): Either<MovieDetailsResponse?, ErrorResponse?> =
        tryRemote {
            service.getMovieDetails(movieId, API_KEY)
        }
}