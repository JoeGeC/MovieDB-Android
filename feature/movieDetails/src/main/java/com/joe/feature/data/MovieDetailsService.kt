package com.joe.feature.data

import com.joe.feature.repository.response.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsService {

    @GET("/movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String
    ): MovieDetailsResponse

}