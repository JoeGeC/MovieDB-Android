package com.joe.data

import com.joe.data.response.ErrorResponse
import com.joe.data.response.Result
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

abstract class BaseRemote {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "40e668e12304e6a05b34c2ea7d7196ce"

        private val gson: Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    suspend fun <T> tryRemote(remoteCall: suspend () -> T) : Result<T?, ErrorResponse?> =
        try{
            val result = remoteCall()
            Result.Success(result)
        } catch(exception: Exception){
            val error = ErrorResponse(exception.localizedMessage ?: "")
            Result.Failure(error)
        }
}