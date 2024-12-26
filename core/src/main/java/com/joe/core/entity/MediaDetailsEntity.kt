package com.joe.core.entity

import java.time.LocalDate

data class MediaDetailsEntity(
    val id: Int,
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
