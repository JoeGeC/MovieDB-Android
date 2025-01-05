package com.joe.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.joe.data.json.LocalDateAdapter
import com.joe.data.json.NetworkProvider
import com.joe.data.response.ErrorResponse
import retrofit2.Response
import java.time.LocalDate

object JsonAdapter{
    fun <T> convertToError(response: Response<T>) : ErrorResponse {
        val type = object : TypeToken<ErrorResponse>() {}.type
        return NetworkProvider.gson.fromJson(response.errorBody()!!.charStream(), type)
    }

    val localDateGson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
        .create()
}