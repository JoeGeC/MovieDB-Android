package com.joe.popularmovies.local.dao

import com.joe.popular.local.dao.PopularDaoHelper
import com.joe.popularmovies.local.model.PopularMoviesLocalModel
import javax.inject.Inject

class PopularMoviesDaoHelper @Inject constructor(
    private val dao: PopularMoviesDao
): PopularDaoHelper<PopularMoviesLocalModel> {

    override fun getByPage(page: Int, validTime: Long): PopularMoviesLocalModel? =
        dao.getByPage(page, validTime)

    override fun insertAll(item: PopularMoviesLocalModel) =
        dao.insertAll(item)

}