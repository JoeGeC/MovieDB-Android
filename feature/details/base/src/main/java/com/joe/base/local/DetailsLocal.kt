package com.joe.base.local

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse

interface DetailsLocal<LocalModel> {
    fun getById(movieId: Int): Either<LocalModel?, ErrorResponse?>
    fun insert(response: LocalModel?)
}