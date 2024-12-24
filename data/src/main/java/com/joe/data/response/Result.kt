package com.joe.data.response

import com.joe.core.entity.ErrorEntity

sealed class Result<out S, out F> {
    data class Success<out S>(val value: S) : Result<S, Nothing>()
    data class Failure<out F>(val error: F) : Result<Nothing, F>() {
        fun convert(): ErrorEntity {
            return ErrorEntity((this.error as ErrorResponse).statusMessage)
        }
    }

    val isSuccess get() = this is Success<S>
    val isFailure get() = this is Failure<F>
    val body get() = (this as Success).value
    val errorBody get() = (this as Failure).error
}