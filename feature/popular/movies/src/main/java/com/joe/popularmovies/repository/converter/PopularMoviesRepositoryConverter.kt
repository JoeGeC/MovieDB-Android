package com.joe.popularmovies.repository.converter

import com.google.gson.reflect.TypeToken
import com.joe.core.entity.toLocalDateOrNull
import com.joe.data.JsonAdapter.localDateGson
import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.domain.entity.MediaListItemEntity
import com.joe.popular.repository.converter.PopularRepositoryConverter
import com.joe.popularmovies.local.model.PopularMoviesLocalModel
import com.joe.popularmovies.repository.response.MovieListItemResponse
import com.joe.popularmovies.repository.response.PopularMoviesResponse
import java.time.LocalDate

class PopularMoviesRepositoryConverter: PopularRepositoryConverter<PopularMoviesLocalModel, PopularMoviesResponse> {

    override fun remoteToEntity(response: PopularMoviesResponse?): MediaListEntity? {
        if(response == null) return null
        return MediaListEntity(
            response.page ?: throw NullPointerException(),
            response.results?.mapNotNull { tryConvertItem(it) } ?: throw IllegalStateException(),
            isLastPage(response.totalPages, response.page)
        )
    }

    private fun isLastPage(totalPages: Int, page: Int): Boolean = totalPages <= page

    private fun tryConvertItem(response: MovieListItemResponse): MediaListItemEntity? = try {
        response.toEntity()
    } catch (_: Exception) {
        null
    }

    override fun remoteToLocal(response: PopularMoviesResponse?): PopularMoviesLocalModel? {
        if(response == null) return null
        return PopularMoviesLocalModel(
            response.page ?: throw NullPointerException(),
            localDateGson.toJson(response.results),
            response.totalPages,
            System.currentTimeMillis()
        )
    }

    override fun localToEntity(localModel: PopularMoviesLocalModel?): MediaListEntity? {
        if(localModel == null) return null
        val items = localDateGson.fromJson<List<MediaListItemEntity>>(
            localModel.movies,
            object : TypeToken<List<MediaListItemEntity>>() {}.type
        ).filterNotNull()
        return MediaListEntity(
            localModel.page,
            items,
            isLastPage(localModel.totalPages, localModel.page)
        )
    }
}

fun MovieListItemResponse.toEntity() = MediaListItemEntity(
    id = this.id ?: throw NullPointerException(),
    title = this.title ?: throw NullPointerException(),
    releaseDate = this.releaseDate?.toLocalDateOrNull(),
    posterPath = this.posterPath,
    score = this.voteAverage
)