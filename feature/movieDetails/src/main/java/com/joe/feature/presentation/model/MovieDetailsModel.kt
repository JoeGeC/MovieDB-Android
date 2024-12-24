package com.joe.feature.presentation.model

import com.joe.feature.domain.entity.MediaType

data class MovieDetailsModel(
    val id: Long,
    val title: String,
    val releaseDate: String,
    val tagline: String,
    val overview: String,
    val posterPath: String,
    val score: Float,
    val backdropPath: String,
    val type: MediaType
)