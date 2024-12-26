package com.joe.core.converter

import com.joe.core.formatter.formatLocalDate
import com.joe.core.formatter.toImageUrl
import com.joe.core.entity.MediaDetailsEntity
import com.joe.core.model.MediaDetailsModel
import java.util.Locale

fun MediaDetailsEntity.toModel(locale: Locale = Locale.getDefault()): MediaDetailsModel =
    MediaDetailsModel(
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