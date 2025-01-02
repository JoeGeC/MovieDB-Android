package com.joe.popularmovies.local.converter

import com.joe.popularmovies.local.model.MovieListItemLocalModel
import com.joe.popularmovies.repository.response.MovieListItemResponse

fun MovieListItemResponse.toLocal() = MovieListItemLocalModel(
    id = this.id ?: throw NullPointerException(),
    title = this.title,
    releaseDate = this.releaseDate,
    posterPath = this.posterPath,
    voteAverage = this.voteAverage
)

fun MovieListItemLocalModel.toResponse() = MovieListItemResponse(
    id = this.id,
    title = this.title,
    releaseDate = this.releaseDate,
    posterPath = this.posterPath,
    voteAverage = this.voteAverage
)
