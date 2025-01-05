package com.joe.popular.presentation.converter

import com.joe.popular.domain.entity.MediaListItemEntity
import com.joe.popular.presentation.model.MediaListItemModel
import com.joe.presentation.formatter.formatLocalDate
import com.joe.presentation.formatter.toImageUrl
import java.util.Locale

fun MediaListItemEntity.toModel(locale: Locale = Locale.getDefault()) = MediaListItemModel(
    id = this.id,
    title = this.title ?: "",
    releaseDate = this.releaseDate?.formatLocalDate(locale) ?: "TBA",
    posterPath = this.posterPath?.toImageUrl() ?: "",
    score = this.score
)