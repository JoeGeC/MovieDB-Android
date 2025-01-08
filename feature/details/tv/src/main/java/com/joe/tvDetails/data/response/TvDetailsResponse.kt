package com.joe.tvDetails.data.response

data class TvDetailsResponse(
    val id: Int?,
    val name: String?,
    val tagline: String?,
    val overview: String?,
    val firstAirDate: String?,
    val lastAirDate: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Float?,
    val numberOfSeasons: Int?,
    val numberOfEpisodes: Int?,
    val inProduction: Boolean?,
)