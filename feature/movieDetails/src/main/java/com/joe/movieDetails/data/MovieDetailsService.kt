package com.joe.movieDetails.data

import com.joe.movieDetails.repository.response.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Call

interface MovieDetailsService {

    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetailsResponse>

}