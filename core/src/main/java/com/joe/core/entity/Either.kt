package com.joe.core.entity

sealed class Either<out S, out F> {
    data class Success<out S>(val value: S) : Either<S, Nothing>()
    data class Failure<out F>(val error: F) : Either<Nothing, F>()

    val isSuccess get() = this is Success<S>
    val isFailure get() = this is Failure<F>
    val body get() = (this as Success).value
    val errorBody get() = (this as Failure).error

    inline fun <R> fold(onSuccess: (S) -> R, onFailure: (F) -> R): R = when (this) {
        is Success -> onSuccess(value)
        is Failure -> onFailure(error)
    }
}