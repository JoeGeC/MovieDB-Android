package com.joe.populartvshows.data

import com.joe.populartvshows.data.response.PopularTvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularTvShowsService {

    @GET("tv/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") id: Int,
    ): Call<PopularTvShowsResponse>

}