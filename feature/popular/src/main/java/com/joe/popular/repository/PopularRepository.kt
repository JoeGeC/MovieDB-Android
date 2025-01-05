package com.joe.popular.repository

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popular.domain.entity.MediaListEntity

interface PopularRepository {
    suspend fun getItems(page: Int): Either<MediaListEntity?, ErrorEntity?>
}