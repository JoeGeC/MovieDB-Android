package com.joe.feature.domain.entity

import java.time.LocalDate

data class MovieDetails(
    val id: Long,
    val title: String,
    val releaseDate: LocalDate,
    val tagline: String,
    val overview: String,
    val posterPath: String,
    val score: Float,
    val backdropPath: String,
    val type: MediaType
)

enum class MediaType {
    Movie, Tv
}
