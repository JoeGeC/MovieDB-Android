package com.joe.movieDetails.local.converter

import com.joe.movieDetails.local.model.MovieDetailsLocalModel
import com.joe.movieDetails.repository.response.MovieDetailsResponse


fun MovieDetailsResponse.toLocal(): MovieDetailsLocalModel = MovieDetailsLocalModel(
    id = this.id ?: throw NullPointerException(),
    title = this.title,
    tagline = this.tagline,
    overview = this.overview,
    releaseDate = this.releaseDate,
    posterPath = this.posterPath,
    voteAverage = this.voteAverage,
    backdropPath = this.backdropPath
)


fun MovieDetailsLocalModel.toResponse() = MovieDetailsResponse(
    id = this.id,
    title = this.title,
    tagline = this.tagline,
    overview = this.overview,
    releaseDate = this.releaseDate,
    posterPath = this.posterPath,
    voteAverage = this.voteAverage,
    backdropPath = this.backdropPath,
)