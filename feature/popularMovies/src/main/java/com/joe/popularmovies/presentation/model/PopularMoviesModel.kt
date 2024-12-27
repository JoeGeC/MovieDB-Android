package com.joe.popularmovies.presentation.model

data class PopularMoviesModel(
    val page: Int,
    val movies: List<com.joe.presentation.model.MediaDetailsModel>,
    val isFinalPage: Boolean,
)