package com.joe.cast.repository

import com.joe.cast.domain.entity.CastListEntity
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity

interface CastRepository {
    suspend fun getCastOf(mediaId: Int): Either<CastListEntity?, ErrorEntity?>
}