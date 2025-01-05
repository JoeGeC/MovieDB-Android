package com.joe.movieDetails.local.dao

import com.joe.base.local.dao.DetailsDaoHelper
import com.joe.movieDetails.local.model.MovieDetailsLocalModel
import javax.inject.Inject

class MovieDetailsDaoHelper @Inject constructor(
    private val dao: MovieDetailsDao
): DetailsDaoHelper<MovieDetailsLocalModel> {

    override fun getById(mediaId: Int): MovieDetailsLocalModel? = dao.getById(mediaId)

    override fun insert(item: MovieDetailsLocalModel) = dao.insertAll(item)

}