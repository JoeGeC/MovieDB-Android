package com.joe.moviesCast.data

import com.joe.cast.data.response.CastListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieCastService {

    @GET("movie/{id}/credits")
    fun getMovieCast(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<CastListResponse>

}