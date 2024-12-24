package com.joe.feature.presentation.converter

import com.joe.core.formatter.formatLocalDate
import com.joe.core.formatter.toImageUrl
import com.joe.feature.domain.entity.MovieDetailsEntity
import com.joe.feature.presentation.model.MovieDetailsModel
import java.util.Locale

fun MovieDetailsEntity.toModel(locale: Locale = Locale.getDefault()): MovieDetailsModel =
    MovieDetailsModel(
        id = this.id,
        title = this.title,
        releaseDate = this.releaseDate.formatLocalDate(locale),
        tagline = this.tagline,
        overview = this.overview,
        posterPath = this.posterPath.toImageUrl(),
        score = this.score,
        backdropPath = this.backdropPath.toImageUrl(),
        type = this.type
    )