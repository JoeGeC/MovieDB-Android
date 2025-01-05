package com.joe.popular.local.model

interface TimeLimitCacheModel {
    val cachedAt: Long
    fun copyWith(cachedAt: Long): TimeLimitCacheModel
}