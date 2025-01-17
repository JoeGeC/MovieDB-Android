package com.joe.popular.local

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse

interface PopularLocal<LocalModel> {
    fun get(page: Int): Either<LocalModel?, ErrorResponse?>
    fun insert(items: LocalModel?)
}