package com.joe.cast.presentation.converter

import com.joe.cast.domain.entity.CastListEntity
import com.joe.cast.domain.entity.ActorEntity
import com.joe.cast.domain.entity.CrewEntity
import com.joe.cast.presentation.model.CastListModel
import com.joe.cast.presentation.model.ActorModel
import com.joe.cast.presentation.model.CrewModel
import com.joe.presentation.formatter.toImageUrl

class CastPresentationConverterImpl : CastPresentationConverter {

    override fun entityToModel(entity: CastListEntity): CastListModel =
        CastListModel(
            id = entity.id,
            cast = entity.cast.mapNotNull { it.toModel() },
            crew = entity.crew.mapNotNull { it.toModel() }
        )

}

private fun ActorEntity.toModel(): ActorModel? = try {
    ActorModel(
        id = this.id,
        name = this.name,
        character = this.character,
        profilePath = this.profilePath?.toImageUrl()
    )
} catch (_: Exception){
    null
}


private fun CrewEntity.toModel(): CrewModel? = try {
    CrewModel(
        id = this.id,
        name = this.name,
        job = this.job,
        profilePath = this.profilePath?.toImageUrl()
    )
} catch (_: Exception){
    null
}
