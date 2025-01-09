package com.joe.populartvshows.data.response

data class TvShowListItemResponse(
    val id: Int?,
    val name: String?,
    val firstAirDate: String?,
    val posterPath: String?,
    val voteAverage: Float?,
)