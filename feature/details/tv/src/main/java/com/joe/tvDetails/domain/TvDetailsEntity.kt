package com.joe.tvDetails.domain

import java.time.LocalDate

data class TvDetailsEntity(
    val id: Int,
    val title: String,
    val releaseDate: LocalDate?,
    val tagline: String?,
    val overview: String?,
    val posterPath: String?,
    val score: Float?,
    val backdropPath: String?,
)