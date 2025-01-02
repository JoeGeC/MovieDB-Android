package com.joe.popularmovies.domain.entity

data class PopularMoviesEntity(
    val page: Int,
    val movies: List<MovieListItemEntity>,
    val isFinalPage: Boolean
)