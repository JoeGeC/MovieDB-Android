package com.joe.tvDetails.data

import com.joe.tvDetails.repository.response.TvDetailsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvDetailsService {

    @GET("tv/{id}")
    fun getTvDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<TvDetailsResponse>

}