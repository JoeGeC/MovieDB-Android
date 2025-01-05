package com.joe.movieDetails.repository.converter

import com.joe.core.entity.MediaDetailsEntity
import com.joe.core.entity.MediaType
import com.joe.movieDetails.repository.response.MovieDetailsResponse
import java.time.LocalDate

fun MovieDetailsResponse.toEntity(): MediaDetailsEntity? = MediaDetailsEntity(
    id = this.id ?: throw NullPointerException(),
    title = this.title ?: throw NullPointerException(),
    overview = this.overview,
    releaseDate = LocalDate.parse(this.releaseDate),
    posterPath = this.posterPath,
    backdropPath = this.backdropPath,
    score = this.voteAverage,
    tagline = this.tagline,
    type = MediaType.Movie
)
