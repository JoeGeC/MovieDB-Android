package com.joe.popular.repository.converter

import com.joe.popular.domain.entity.MediaListEntity

interface PaginatedRepositoryConverter<LocalModel, Response> {
    fun localToEntity(model: LocalModel?): MediaListEntity?
    fun remoteToLocal(response: Response?): LocalModel?
    fun remoteToEntity(response: Response?): MediaListEntity?
}