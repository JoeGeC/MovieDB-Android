package com.joe.data

import com.joe.data.response.ErrorResponse
import com.joe.data.response.Result
import retrofit2.Call
import retrofit2.Retrofit
import java.lang.Exception

abstract class BaseRemote(
    protected val retrofit: Retrofit = NetworkProvider.createRetrofit()
) {

    fun <T> tryRemote(remoteCall: () -> Call<T>) : Result<T?, ErrorResponse?> =
        try{
            val result = remoteCall.invoke().execute()
            println("Called")
            if (result.isSuccessful) {
                Result.Success(result.body())
            } else {
                val errorResponse = JsonAdapter.convertToError(result)
                Result.Failure(errorResponse)
            }
        } catch(exception: Exception){
            val error = ErrorResponse(exception.localizedMessage ?: "")
            Result.Failure(error)
        }
}