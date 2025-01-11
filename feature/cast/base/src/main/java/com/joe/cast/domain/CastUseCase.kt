package com.joe.cast.domain

import com.joe.cast.domain.entity.CastListEntity
import com.joe.cast.repository.CastRepository
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity

class CastUseCase(
    private val repository: CastRepository
) {

    suspend fun getCastOf(mediaId: Int): Either<CastListEntity?, ErrorEntity?> =
        repository.getCastOf(mediaId)
}