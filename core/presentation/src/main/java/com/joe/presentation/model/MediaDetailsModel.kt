package com.joe.presentation.model

import com.joe.core.entity.MediaType

data class MediaDetailsModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val tagline: String,
    val overview: String,
    val posterPath: String?,
    val score: Float?,
    val backdropPath: String?,
    val type: MediaType
)