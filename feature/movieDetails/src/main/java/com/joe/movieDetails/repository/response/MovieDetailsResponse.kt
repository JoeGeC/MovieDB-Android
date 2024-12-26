package com.joe.movieDetails.repository.response

data class MovieDetailsResponse(
    val id: Long?,
    val title: String?,
    val tagline: String?,
    val overview: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val voteAverage: Float?,
    val backdropPath: String?
)