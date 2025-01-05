package com.joe.populartvshows.local.dao

import com.joe.popular.local.dao.PopularDaoHelper
import com.joe.populartvshows.local.model.PopularTvShowsLocalModel
import javax.inject.Inject

class PopularTvShowsDaoHelper @Inject constructor(
    private val dao: PopularTvShowsDao
): PopularDaoHelper<PopularTvShowsLocalModel> {

    override fun getByPage(page: Int, validTime: Long): PopularTvShowsLocalModel? =
        dao.getByPage(page, validTime)

    override fun insertAll(item: PopularTvShowsLocalModel) = dao.insertAll(item)

}