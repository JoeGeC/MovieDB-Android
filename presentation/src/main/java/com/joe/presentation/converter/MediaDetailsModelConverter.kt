package com.joe.presentation.converter

import com.joe.presentation.formatter.formatLocalDate
import com.joe.presentation.formatter.toImageUrl
import com.joe.core.entity.MediaDetailsEntity
import com.joe.core.entity.MediaType
import com.joe.presentation.model.MediaDetailsModel
import java.util.Locale

fun MediaDetailsEntity.toModel(locale: Locale = Locale.getDefault()): MediaDetailsModel =
    MediaDetailsModel(
        id = this.id,
        title = this.title,
        releaseDate = this.releaseDate?.formatLocalDate(locale) ?: "",
        tagline = this.tagline ?: "",
        overview = this.overview ?: "",
        posterPath = this.posterPath?.toImageUrl(),
        score = this.score,
        backdropPath = this.backdropPath?.toImageUrl(),
        type = this.type ?: MediaType.Movie
    )