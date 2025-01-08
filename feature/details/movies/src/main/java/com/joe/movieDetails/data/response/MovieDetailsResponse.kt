package com.joe.movieDetails.data.response

data class MovieDetailsResponse(
    val id: Int?,
    val title: String?,
    val tagline: String?,
    val overview: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val voteAverage: Float?,
    val backdropPath: String?
)