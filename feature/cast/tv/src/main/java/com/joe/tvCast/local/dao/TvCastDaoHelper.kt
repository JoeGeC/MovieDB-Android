package com.joe.tvCast.local.dao

import com.joe.cast.local.CastDaoHelper
import com.joe.cast.local.model.CastListLocalModel

class TvCastDaoHelper(
    private val dao: TvCastDao
): CastDaoHelper {

    override fun getById(mediaId: Int): CastListLocalModel? = dao.getById(mediaId)

    override fun insert(model: CastListLocalModel) = dao.insertAll(model)

}