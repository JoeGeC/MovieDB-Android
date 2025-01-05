package com.joe.popularmovies.repository.response

data class MovieListItemResponse(
    val id: Int?,
    val title: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val voteAverage: Float?,
)