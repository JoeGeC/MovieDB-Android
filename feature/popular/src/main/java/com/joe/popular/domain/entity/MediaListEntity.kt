package com.joe.popular.domain.entity

data class MediaListEntity(
    val page: Int,
    val shows: List<MediaListItemEntity>,
    val isFinalPage: Boolean
)