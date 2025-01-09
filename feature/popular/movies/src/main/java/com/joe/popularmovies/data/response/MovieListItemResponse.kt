package com.joe.popularmovies.data.response

data class MovieListItemResponse(
    val id: Int?,
    val title: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val voteAverage: Float?,
)