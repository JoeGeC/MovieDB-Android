package com.joe.tvDetails.local.dao

import com.joe.base.local.dao.DetailsDaoHelper
import com.joe.tvDetails.local.model.TvDetailsLocalModel
import javax.inject.Inject

class TvDetailsDaoHelper @Inject constructor(
    private val dao: TvDetailsDao
): DetailsDaoHelper<TvDetailsLocalModel> {

    override fun getById(mediaId: Int): TvDetailsLocalModel? = dao.getById(mediaId)

    override fun insert(item: TvDetailsLocalModel) = dao.insertAll(item)

}