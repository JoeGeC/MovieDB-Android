package com.joe.popular.domain

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.repository.PaginatedRepository
import javax.inject.Inject

class PaginatedUseCase @Inject constructor(
    private val repository: PaginatedRepository
) {
    suspend fun getItems(page: Int): Either<MediaListEntity?, ErrorEntity?> =
        repository.getItems(page)
}