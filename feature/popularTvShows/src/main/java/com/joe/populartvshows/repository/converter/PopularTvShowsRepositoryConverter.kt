package com.joe.populartvshows.repository.converter

import com.google.gson.reflect.TypeToken
import com.joe.data.JsonAdapter.localDateGson
import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.domain.entity.MediaListItemEntity
import com.joe.popular.repository.converter.PaginatedRepositoryConverter
import com.joe.populartvshows.local.model.PopularTvShowsLocalModel
import com.joe.populartvshows.repository.response.PopularTvShowsResponse
import com.joe.populartvshows.repository.response.TvShowListItemResponse
import java.time.LocalDate

class PopularTvShowsRepositoryConverter: PaginatedRepositoryConverter<PopularTvShowsLocalModel, PopularTvShowsResponse> {
    override fun remoteToEntity(response: PopularTvShowsResponse?): MediaListEntity? {
        if(response == null) return null
        return MediaListEntity(
            response.page ?: throw NullPointerException(),
            response.results?.mapNotNull { tryConvertMovie(it) } ?: throw IllegalStateException(),
            isLastPage(response.totalPages, response.page)
        )
    }

    private fun isLastPage(totalPages: Int, page: Int): Boolean = totalPages <= page

    private fun tryConvertMovie(response: TvShowListItemResponse): MediaListItemEntity? = try {
        response.toEntity()
    } catch (_: Exception) {
        null
    }

    override fun remoteToLocal(response: PopularTvShowsResponse?): PopularTvShowsLocalModel? {
        if(response == null) return null
        return PopularTvShowsLocalModel(
            response.page ?: throw NullPointerException(),
            localDateGson.toJson(response.results),
            response.totalPages,
            System.currentTimeMillis()
        )
    }

    override fun localToEntity(localModel: PopularTvShowsLocalModel?): MediaListEntity? {
        if(localModel == null) return null
        return MediaListEntity(
            localModel.page,
            localDateGson.fromJson(localModel.shows, object: TypeToken<List<MediaListItemEntity>>() {}.type),
            isLastPage(localModel.totalPages, localModel.page)
        )
    }
}

fun TvShowListItemResponse.toEntity() = MediaListItemEntity(
    id = this.id ?: throw NullPointerException(),
    title = this.name ?: throw NullPointerException(),
    releaseDate = LocalDate.parse(this.firstAirDate),
    posterPath = this.posterPath ?: throw NullPointerException(),
    score = this.voteAverage ?: throw java.lang.NullPointerException()
)