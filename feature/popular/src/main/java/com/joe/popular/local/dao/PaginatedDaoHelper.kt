package com.joe.popular.local.dao

import com.joe.popular.local.model.TimeLimitCacheModel

interface PaginatedDaoHelper<T: TimeLimitCacheModel> {
    fun getByPage(page: Int, validTime: Long): T?
    fun insertAll(item: T)
}