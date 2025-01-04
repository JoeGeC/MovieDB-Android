package com.joe.popular.repository

import com.joe.popular.domain.entity.MediaListEntity

interface PaginatedRepositoryConverter<LocalModel, Response> {
    fun localToEntity(model: LocalModel?): MediaListEntity?
    fun remoteToLocal(response: Response?): LocalModel?
}