package com.joe.cast.repository.converter

import com.google.gson.reflect.TypeToken
import com.joe.cast.data.response.CastListResponse
import com.joe.cast.data.response.ActorResponse
import com.joe.cast.data.response.CrewResponse
import com.joe.cast.domain.entity.CastListEntity
import com.joe.cast.domain.entity.ActorEntity
import com.joe.cast.domain.entity.CrewEntity
import com.joe.cast.local.model.CastListLocalModel
import com.joe.data.JsonAdapter.localDateGson

class CastRepositoryConverter {

    fun localToEntity(localListModel: CastListLocalModel?): CastListEntity? {
        return localListModel?.let {
            CastListEntity(
                id = it.id,
                cast = convertActorFromJson(it.cast),
                crew = convertCrewFromJson(it.crew)
            )
        }
    }

    fun convertActorFromJson(listJson: String): List<ActorEntity> =
        localDateGson.fromJson<List<ActorEntity>>(
            listJson,
            object : TypeToken<List<ActorEntity>>() {}.type
        ).filterNotNull()

    fun convertCrewFromJson(listJson: String): List<CrewEntity> =
        localDateGson.fromJson<List<CrewEntity>>(
            listJson,
            object : TypeToken<List<CrewEntity>>() {}.type
        ).filterNotNull()

    fun remoteToLocal(response: CastListResponse?): CastListLocalModel? =
        CastListLocalModel(
            id = response?.id ?: throw NullPointerException(),
            cast = localDateGson.toJson(response.cast),
            crew = localDateGson.toJson(response.crew)
        )

    fun remoteToEntity(response: CastListResponse?): CastListEntity? =
        response?.let {
            CastListEntity(
                id = it.id,
                cast = it.cast.mapNotNull { actor -> actor.toEntity() },
                crew = it.crew.mapNotNull { crew -> crew.toEntity() }
            )
        }

}

private fun ActorResponse.toEntity(): ActorEntity? = try {
    ActorEntity(
        id = this.id,
        name = this.name ?: throw NullPointerException(),
        character = this.character,
        profilePath = this.profilePath
    )
} catch (_: Exception) {
    null
}

private fun CrewResponse.toEntity(): CrewEntity? = try {
    CrewEntity(
        id = this.id,
        name = this.name ?: throw NullPointerException(),
        job = this.job,
        profilePath = this.profilePath
    )
} catch (_: Exception) {
    null
}