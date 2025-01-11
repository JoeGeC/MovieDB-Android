package com.joe.cast.repository.converter

import com.google.gson.reflect.TypeToken
import com.joe.cast.data.response.CastListResponse
import com.joe.cast.data.response.PersonResponse
import com.joe.cast.domain.entity.CastListEntity
import com.joe.cast.domain.entity.PersonEntity
import com.joe.cast.local.model.CastListLocalModel
import com.joe.data.JsonAdapter.localDateGson

class CastRepositoryConverter {

    fun localToEntity(localListModel: CastListLocalModel?): CastListEntity? {
        return localListModel?.let {
            CastListEntity(
                id = it.id,
                cast = convertFromJson(it.cast),
                crew = convertFromJson(it.crew)
            )
        }
    }

    fun convertFromJson(listJson: String): List<PersonEntity> =
        localDateGson.fromJson<List<PersonEntity>>(
            listJson,
            object : TypeToken<List<PersonEntity>>() {}.type
        ).filterNotNull()

    fun remoteToLocal(response: CastListResponse?): CastListLocalModel =
        CastListLocalModel(
            id = response?.id ?: throw NullPointerException(),
            cast = localDateGson.toJson(response.cast),
            crew = localDateGson.toJson(response.crew)
        )

    fun remoteToEntity(response: CastListResponse?): CastListEntity? =
        response?.let {
            CastListEntity(
                id = it.id,
                cast = it.cast.mapNotNull { person -> person.toEntity() },
                crew = it.crew.mapNotNull { person -> person.toEntity() }
            )
        }

}

private fun PersonResponse.toEntity(): PersonEntity? = try {
    PersonEntity(
        id = this.id,
        name = this.name,
        character = this.character,
        profilePath = this.profilePath
    )
} catch (_: Exception) {
    null
}