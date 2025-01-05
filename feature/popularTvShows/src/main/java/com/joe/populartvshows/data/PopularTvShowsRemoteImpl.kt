package com.joe.populartvshows.data

import com.joe.core.entity.Either
import com.joe.data.BaseRemote
import com.joe.data.json.NetworkProvider.API_KEY
import com.joe.data.response.ErrorResponse
import com.joe.popular.data.PopularRemote
import com.joe.populartvshows.repository.response.PopularTvShowsResponse
import com.joe.populartvshows.repository.response.TvShowListItemResponse
import retrofit2.Retrofit

class PopularTvShowsRemoteImpl(retrofit: Retrofit) : BaseRemote(), PopularRemote<TvShowListItemResponse, PopularTvShowsResponse> {
    private val service: PopularTvShowsService = retrofit.create(
        PopularTvShowsService::class.java
    )

    override fun getItems(page: Int): Either<PopularTvShowsResponse?, ErrorResponse?> =
        tryRemote {
            service.getPopularMovies(API_KEY, page)
        }
}