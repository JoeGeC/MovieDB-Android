package com.joe.popularmovies.presentation.model

import com.joe.core.model.MediaDetailsModel

data class PopularMoviesModel(
    val page: Int,
    val movies: List<MediaDetailsModel>,
    val isFinalPage: Boolean,
)