package com.joe.populartvshows.repository.converter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.joe.popular.domain.entity.MediaListItemEntity
import java.lang.reflect.Type
import java.time.LocalDate

class MediaListItemEntityDeserializer : JsonDeserializer<MediaListItemEntity> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): MediaListItemEntity? =
        try {
            val jsonObject = json.asJsonObject
            MediaListItemEntity(
                id = jsonObject.get("id").asInt,
                title = jsonObject.get("name").asString,
                releaseDate = jsonObject.get("firstAirDate")?.asString?.let { LocalDate.parse(it) },
                posterPath = jsonObject.get("posterPath").asString,
                score = jsonObject.get("voteAverage")?.asFloat ?: 0f
            )
        } catch (_: Exception){ null }
}
