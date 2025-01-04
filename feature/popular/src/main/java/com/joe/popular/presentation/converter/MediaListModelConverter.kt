package com.joe.popular.presentation.converter

import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.presentation.model.MediaListItemModel
import com.joe.popular.presentation.model.MediaListModel
import java.util.Locale

fun MediaListEntity.toModel(
    locale: Locale = Locale.getDefault()
): MediaListModel =
    MediaListModel(
        page = this.page,
        items = convertList(locale),
        isFinalPage = this.isFinalPage,
    )

private fun MediaListEntity.convertList(locale: Locale): List<MediaListItemModel> =
    this.items.map { mediaItem -> mediaItem.toModel(locale) }