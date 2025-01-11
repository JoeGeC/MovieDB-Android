package com.joe.cast.data

import com.joe.cast.data.response.CastListResponse
import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse

interface CastRemote {
    fun getCastOf(mediaId: Int): Either<CastListResponse?, ErrorResponse?>
}