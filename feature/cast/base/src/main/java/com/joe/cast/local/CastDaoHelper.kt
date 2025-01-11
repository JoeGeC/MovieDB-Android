package com.joe.cast.local

import com.joe.cast.local.model.CastListLocalModel

interface CastDaoHelper {
    fun getById(mediaId: Int): CastListLocalModel?
    fun insert(model: CastListLocalModel)
}