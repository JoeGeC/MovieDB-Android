package com.joe.popularmovies.data

import com.joe.popularmovies.data.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") id: Int,
    ): Call<PopularMoviesResponse>

}