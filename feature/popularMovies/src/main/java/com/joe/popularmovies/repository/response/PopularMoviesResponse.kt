package com.joe.popularmovies.repository.response

data class PopularMoviesResponse(
    val page: Int?,
    val movies: List<MovieListItemResponse>?,
    val isFinalPage: Boolean,
)