package com.joe.popularmovies.data

import com.joe.core.entity.Either
import com.joe.data.BaseRemote
import com.joe.data.json.NetworkProvider.API_KEY
import com.joe.data.response.ErrorResponse
import com.joe.popular.data.PopularRemote
import com.joe.popularmovies.data.response.MovieListItemResponse
import com.joe.popularmovies.data.response.PopularMoviesResponse
import retrofit2.Retrofit

class PopularMoviesRemoteImpl(retrofit: Retrofit) : BaseRemote(), PopularRemote<MovieListItemResponse, PopularMoviesResponse> {
    private val service: PopularMoviesService = retrofit.create(
        PopularMoviesService::class.java
    )

    override fun getItems(page: Int): Either<PopularMoviesResponse?, ErrorResponse?> =
        tryRemote {
            service.getPopularMovies(API_KEY, page)
        }
}