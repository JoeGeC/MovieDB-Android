package com.joe.base.local.dao

interface DetailsDaoHelper<T> {
    fun getById(mediaId: Int): T?
    fun insert(item: T)
}