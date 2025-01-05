package com.joe.popular.local

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.popular.local.dao.PopularDaoHelper
import com.joe.popular.local.model.TimeLimitCacheModel

class PopularLocalImpl<T: TimeLimitCacheModel>(
    private val daoHelper: PopularDaoHelper<T>,
) : PopularLocal<T> {
    private val cacheValidDuration24Hours = 86400000L

    override fun get(page: Int): Either<T?, ErrorResponse> {
        val validTime = System.currentTimeMillis() - cacheValidDuration24Hours
        return try {
            val response = daoHelper.getByPage(page, validTime)
                ?: return Either.Failure(ErrorResponse("Cache expired or no data available"))
            Either.Success(response)
        } catch (e: Exception) {
            Either.Failure(ErrorResponse(e.message ?: "DAO query failed: ${e.localizedMessage}"))
        }
    }

    override fun insert(item: T?) {
        try {
            if (item == null) return
            val updatedItem = item.copy(cachedAt = System.currentTimeMillis()) as T
            daoHelper.insertAll(updatedItem)
        } catch (_: Exception) {}
    }
}
