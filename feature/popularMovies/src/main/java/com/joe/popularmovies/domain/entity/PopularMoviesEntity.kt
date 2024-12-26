package com.joe.popularmovies.domain.entity

import com.joe.core.entity.MediaDetailsEntity

data class PopularMoviesEntity(
    val page: Int,
    val movies: List<MediaDetailsEntity>,
    val isFinalPage: Boolean
)