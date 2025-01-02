package com.joe.data

import com.joe.core.entity.Either
import com.joe.data.json.NetworkProvider
import com.joe.data.response.ErrorResponse
import retrofit2.Call
import retrofit2.Retrofit
import java.lang.Exception

abstract class BaseRemote(
    protected val retrofit: Retrofit = NetworkProvider.createRetrofit()
) {

    fun <T> tryRemote(remoteCall: () -> Call<T>) : Either<T?, ErrorResponse?> =
        try{
            val result = remoteCall.invoke().execute()
            if (result.isSuccessful) {
                Either.Success(result.body())
            } else {
                val errorResponse = JsonAdapter.convertToError(result)
                Either.Failure(errorResponse)
            }
        } catch(exception: Exception){
            val error = ErrorResponse(exception.localizedMessage ?: "")
            Either.Failure(error)
        }
}