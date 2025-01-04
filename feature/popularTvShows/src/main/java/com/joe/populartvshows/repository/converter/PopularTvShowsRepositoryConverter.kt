package com.joe.populartvshows.repository.converter

import com.google.gson.reflect.TypeToken
import com.joe.data.JsonAdapter.localDateGson
import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.domain.entity.MediaListItemEntity
import com.joe.popular.repository.PaginatedRepositoryConverter
import com.joe.populartvshows.local.model.PopularTvShowsLocalModel
import com.joe.populartvshows.repository.response.PopularTvShowsResponse

class PopularTvShowsRepositoryConverter: PaginatedRepositoryConverter<PopularTvShowsLocalModel, PopularTvShowsResponse> {
    override fun localToEntity(model: PopularTvShowsLocalModel?): MediaListEntity? {
        if(model == null) return null
        return MediaListEntity(
            model.page,
            localDateGson.fromJson(model.shows, object: TypeToken<List<MediaListItemEntity>>() {}.type),
            model.totalPages <= model.page
        )
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
}