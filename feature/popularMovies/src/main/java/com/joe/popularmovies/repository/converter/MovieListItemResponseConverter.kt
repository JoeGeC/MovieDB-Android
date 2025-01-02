package com.joe.popularmovies.repository.converter

import com.joe.popularmovies.domain.entity.MovieListItemEntity
import com.joe.popularmovies.repository.response.MovieListItemResponse
import java.time.LocalDate

fun MovieListItemResponse.toEntity() = MovieListItemEntity(
    id = this.id ?: throw NullPointerException(),
    title = this.title ?: throw NullPointerException(),
    releaseDate = LocalDate.parse(this.releaseDate),
    posterPath = this.posterPath ?: throw NullPointerException(),
    score = this.voteAverage ?: throw java.lang.NullPointerException()
)