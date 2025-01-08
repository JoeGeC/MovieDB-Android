package com.joe.tvDetails.repository.response

data class TvDetailsResponse(
    val id: Int?,
    val title: String?,
    val tagline: String?,
    val overview: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val voteAverage: Float?,
    val backdropPath: String?
)