package com.joe.tvDetails.repository.converter

import com.joe.base.repository.converter.DetailsRepositoryConverter
import com.joe.core.entity.toLocalDateOrNull
import com.joe.tvDetails.domain.TvDetailsEntity
import com.joe.tvDetails.local.model.TvDetailsLocalModel
import com.joe.tvDetails.repository.response.TvDetailsResponse

class TvDetailsRepositoryConverter :
    DetailsRepositoryConverter<TvDetailsEntity, TvDetailsLocalModel, TvDetailsResponse> {

    override fun localToEntity(model: TvDetailsLocalModel?): TvDetailsEntity =
        TvDetailsEntity(
            id = model?.id ?: throw NullPointerException(),
            title = model.title ?: throw NullPointerException(),
            tagline = model.tagline,
            overview = model.overview,
            releaseDate = model.releaseDate?.toLocalDateOrNull(),
            posterPath = model.posterPath,
            score = model.voteAverage,
            backdropPath = model.backdropPath,
        )

    override fun remoteToLocal(response: TvDetailsResponse?): TvDetailsLocalModel? =
        TvDetailsLocalModel(
            id = response?.id ?: throw NullPointerException(),
            title = response.title ?: throw NullPointerException(),
            tagline = response.tagline ?: throw NullPointerException(),
            overview = response.overview ?: throw NullPointerException(),
            releaseDate = response.releaseDate ?: throw NullPointerException(),
            posterPath = response.posterPath ?: throw NullPointerException(),
            voteAverage = response.voteAverage ?: throw NullPointerException(),
            backdropPath = response.backdropPath ?: throw NullPointerException()
        )

    override fun remoteToEntity(response: TvDetailsResponse?): TvDetailsEntity =
        TvDetailsEntity(
            id = response?.id ?: throw NullPointerException(),
            title = response.title ?: throw NullPointerException(),
            overview = response.overview,
            releaseDate = response.releaseDate?.toLocalDateOrNull(),
            posterPath = response.posterPath,
            backdropPath = response.backdropPath,
            score = response.voteAverage,
            tagline = response.tagline,
        )

}