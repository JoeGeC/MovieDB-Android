package com.joe.popularmovies.local.model

data class MovieListItemLocalModel(
    val id: Int,
    val title: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val voteAverage: Float?,
)
