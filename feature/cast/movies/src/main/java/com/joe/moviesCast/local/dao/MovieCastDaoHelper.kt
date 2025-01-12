package com.joe.moviesCast.local.dao

import com.joe.cast.local.CastDaoHelper
import com.joe.cast.local.model.CastListLocalModel

class MovieCastDaoHelper(
    private val dao: MovieCastDao
): CastDaoHelper {

    override fun getById(mediaId: Int): CastListLocalModel? = dao.getById(mediaId)

    override fun insert(model: CastListLocalModel) = dao.insertAll(model)

}