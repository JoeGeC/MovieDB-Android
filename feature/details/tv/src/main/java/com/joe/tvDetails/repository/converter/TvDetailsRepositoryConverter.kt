package com.joe.tvDetails.repository.converter

import com.joe.base.repository.converter.DetailsRepositoryConverter
import com.joe.core.entity.toLocalDateOrNull
import com.joe.tvDetails.domain.TvDetailsEntity
import com.joe.tvDetails.local.model.TvDetailsLocalModel
import com.joe.tvDetails.data.response.TvDetailsResponse

class TvDetailsRepositoryConverter :
    DetailsRepositoryConverter<TvDetailsEntity, TvDetailsLocalModel, TvDetailsResponse> {

    override fun localToEntity(model: TvDetailsLocalModel?): TvDetailsEntity =
        TvDetailsEntity(
            id = model?.id ?: throw NullPointerException(),
            name = model.name ?: throw NullPointerException(),
            tagline = model.tagline,
            overview = model.overview,
            firstAirDate = model.firstAirDate?.toLocalDateOrNull(),
            lastAirDate = model.lastAirDate?.toLocalDateOrNull(),
            posterPath = model.posterPath,
            backdropPath = model.backdropPath,
            score = model.voteAverage,
            numberOfSeasons = model.numberOfSeasons,
            numberOfEpisodes = model.numberOfEpisodes,
            inProduction = model.inProduction,
        )

    override fun remoteToLocal(response: TvDetailsResponse?): TvDetailsLocalModel? =
        TvDetailsLocalModel(
            id = response?.id ?: throw NullPointerException(),
            name = response.name ?: throw NullPointerException(),
            tagline = response.tagline,
            overview = response.overview,
            firstAirDate = response.firstAirDate,
            lastAirDate = response.lastAirDate,
            posterPath = response.posterPath,
            voteAverage = response.voteAverage,
            backdropPath = response.backdropPath,
            numberOfSeasons = response.numberOfSeasons,
            numberOfEpisodes = response.numberOfEpisodes,
            inProduction = response.inProduction,
        )

    override fun remoteToEntity(response: TvDetailsResponse?): TvDetailsEntity =
        TvDetailsEntity(
            id = response?.id ?: throw NullPointerException(),
            name = response.name ?: throw NullPointerException(),
            overview = response.overview,
            firstAirDate = response.firstAirDate?.toLocalDateOrNull(),
            lastAirDate = response.lastAirDate?.toLocalDateOrNull(),
            posterPath = response.posterPath,
            backdropPath = response.backdropPath,
            score = response.voteAverage,
            tagline = response.tagline,
            numberOfSeasons = response.numberOfSeasons,
            numberOfEpisodes = response.numberOfEpisodes,
            inProduction = response.inProduction,
        )

}