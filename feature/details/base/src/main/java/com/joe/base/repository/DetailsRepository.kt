package com.joe.base.repository

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity

interface DetailsRepository<Entity> {
    suspend fun getDetails(mediaId: Int) : Either<Entity?, ErrorEntity?>
}
