package com.joe.feature.data

import com.joe.data.BaseRemote
import com.joe.data.response.ErrorResponse
import com.joe.feature.repository.boundary.MovieDetailsRemote
import com.joe.feature.repository.response.MovieDetailsResponse
import com.joe.data.response.Result

class MovieDetailsRemoteImpl(
    private val remote: MovieDetailsService = retrofit.create(
        MovieDetailsService::class.java
    )
) : BaseRemote(), MovieDetailsRemote {

    override suspend fun getMovieDetails(movieId: Long): Result<MovieDetailsResponse?, ErrorResponse?> =
        tryRemote {
            remote.getMovieDetails(movieId, API_KEY)
        }
}