package com.joe.cast.presentation.converter

import com.joe.cast.domain.entity.CastListEntity
import com.joe.cast.presentation.model.CastListModel

interface CastPresentationConverter {
    fun entityToModel(entity: CastListEntity): CastListModel
}