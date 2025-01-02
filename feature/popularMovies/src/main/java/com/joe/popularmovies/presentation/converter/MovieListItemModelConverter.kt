package com.joe.popularmovies.presentation.converter

import com.joe.popularmovies.domain.entity.MovieListItemEntity
import com.joe.popularmovies.presentation.model.MovieListItemModel
import com.joe.presentation.formatter.formatLocalDate
import com.joe.presentation.formatter.toImageUrl
import java.util.Locale

fun MovieListItemEntity.toModel(locale: Locale = Locale.getDefault()) = MovieListItemModel(
    id = this.id,
    title = this.title ?: "",
    releaseDate = this.releaseDate?.formatLocalDate(locale) ?: "",
    posterPath = this.posterPath?.toImageUrl() ?: "",
    score = this.score
)