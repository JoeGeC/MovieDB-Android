package com.joe.popularmovies.repository.response

data class PopularMoviesResponse(
    val page: Int?,
    val results: List<MovieListItemResponse>?,
    val totalPages: Int,
)