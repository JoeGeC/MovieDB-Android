package com.joe.popular.domain

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.repository.PopularRepository
import javax.inject.Inject

class PopularUseCase @Inject constructor(
    private val repository: PopularRepository
) {
    suspend fun getItems(page: Int): Either<MediaListEntity?, ErrorEntity?> =
        repository.getItems(page)
}