package com.joe.base.presentation.converter

interface DetailsPresentationConverter<Model, Entity> {
    fun entityToModel(entity: Entity): Model
}