package com.joe.base.repository.converter

interface DetailsRepositoryConverter<Entity, LocalModel, Response> {
    fun localToEntity(model: LocalModel?): Entity?
    fun remoteToLocal(response: Response?): LocalModel?
    fun remoteToEntity(response: Response?): Entity?
}