package com.joe.movieDetails.presentation.model

import com.joe.base.presentation.MediaDetailsModel

data class MovieDetailsModel(
    override val id: Int,
    override val name: String,
    val releaseDate: String,
    override val tagline: String,
    override val overview: String,
    override val posterPath: String?,
    override val score: Float?,
    override val backdropPath: String?,
): MediaDetailsModel()