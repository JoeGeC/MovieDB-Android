package com.joe.base.usecase

import com.joe.base.repository.DetailsRepository
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity

class DetailsUseCase<Entity>(
    private val repository: DetailsRepository<Entity>
) {
    suspend fun getDetails(mediaId: Int): Either<Entity?, ErrorEntity?> =
        repository.getDetails(mediaId)
}