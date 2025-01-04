package com.joe.popular.domain.entity

data class MediaListEntity(
    val page: Int,
    val items: List<MediaListItemEntity>,
    val isFinalPage: Boolean
)