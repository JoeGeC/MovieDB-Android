package com.joe.popular.local.model

interface TimeLimitCacheModel {
    val cachedAt: Long
    fun copy(cachedAt: Long): TimeLimitCacheModel
}