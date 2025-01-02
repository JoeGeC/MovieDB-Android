package com.joe.data.utils

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import kotlinx.coroutines.flow.FlowCollector


suspend fun <T> FlowCollector<Either<T, ErrorEntity?>>.emitSafeOrFailure(
    block: () -> T
) {
    try {
        emit(Either.Success(block()))
    } catch (e: Exception) {
        emit(Either.Failure(ErrorEntity(e.message ?: "")))
    }
}

suspend fun <T> FlowCollector<Either<T, ErrorEntity?>>.emitSafeOrNone(
    block: () -> T
) {
    try {
        emit(Either.Success(block()))
    } catch (e: Exception) { }
}