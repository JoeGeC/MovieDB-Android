package com.joe.cast.local

import com.joe.cast.local.model.CastListLocalModel
import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse

interface CastLocal {
    fun getCastOf(mediaId: Int): Either<CastListLocalModel?, ErrorResponse?>
    fun insert(castListLocalModel: CastListLocalModel?)
}