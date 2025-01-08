package com.joe.tvDetails.domain

import java.time.LocalDate

data class TvDetailsEntity(
    val id: Int,
    val name: String,
    val tagline: String?,
    val overview: String?,
    val firstAirDate: LocalDate?,
    val lastAirDate: LocalDate?,
    val posterPath: String?,
    val backdropPath: String?,
    val score: Float?,
    val numberOfSeasons: Int?,
    val numberOfEpisodes: Int?,
    val inProduction: Boolean?,
)