package com.joe.tvDetails.presentation.model

import com.joe.base.presentation.MediaDetailsModel

data class TvDetailsModel(
    override val id: Int,
    override val name: String,
    val firstAirDate: String?,
    val lastAirDate: String?,
    override val tagline: String?,
    override val overview: String?,
    override val posterPath: String?,
    override val backdropPath: String?,
    override val score: Float?,
    val numberOfSeasons: Int?,
    val numberOfEpisodes: Int?,
    val inProduction: Boolean?,
): MediaDetailsModel()