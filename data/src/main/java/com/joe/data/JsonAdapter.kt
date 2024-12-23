package com.joe.data

import com.google.gson.reflect.TypeToken
import com.joe.data.response.ErrorResponse
import retrofit2.Response

class JsonAdapter{
    companion object{
        fun <T> convertToError(response: Response<T>) : ErrorResponse {
            val type = object : TypeToken<ErrorResponse>() {}.type
            return NetworkProvider.gson.fromJson(response.errorBody()!!.charStream(), type)
        }
    }
}