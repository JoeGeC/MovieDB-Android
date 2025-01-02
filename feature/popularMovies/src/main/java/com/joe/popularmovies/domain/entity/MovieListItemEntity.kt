package com.joe.popularmovies.domain.entity

import java.time.LocalDate

data class MovieListItemEntity(
    val id: Int,
    val title: String?,
    val releaseDate: LocalDate?,
    val posterPath: String?,
    val score: Float,
)