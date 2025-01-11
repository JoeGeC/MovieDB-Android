package com.joe.cast.presentation.converter

import com.joe.cast.domain.entity.CastListEntity
import com.joe.cast.domain.entity.PersonEntity
import com.joe.cast.presentation.model.CastListModel
import com.joe.cast.presentation.model.PersonModel
import com.joe.presentation.formatter.toImageUrl

class CastPresentationConverterImpl : CastPresentationConverter {

    override fun entityToModel(entity: CastListEntity): CastListModel =
        CastListModel(
            id = entity.id,
            cast = entity.cast.mapNotNull { it.toModel() },
            crew = entity.crew.mapNotNull { it.toModel() }
        )

}

private fun PersonEntity.toModel(): PersonModel? = try {
    PersonModel(
        id = this.id,
        name = this.name,
        character = this.character,
        profilePath = this.profilePath?.toImageUrl()
    )
} catch (_: Exception){
    null
}
