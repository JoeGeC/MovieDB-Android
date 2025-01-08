package com.joe.tvDetails.presentation.converter

import com.joe.base.presentation.converter.DetailsPresentationConverter
import com.joe.presentation.formatter.formatLocalDate
import com.joe.presentation.formatter.toImageUrl
import com.joe.tvDetails.domain.TvDetailsEntity
import com.joe.tvDetails.presentation.model.TvDetailsModel
import java.util.Locale

class TvDetailsPresentationConverter(
    private val locale: Locale = Locale.getDefault()
): DetailsPresentationConverter<TvDetailsModel, TvDetailsEntity> {

    override fun entityToModel(entity: TvDetailsEntity): TvDetailsModel =
        TvDetailsModel(
            id = entity.id,
            name = entity.name,
            firstAirDate = entity.firstAirDate?.formatLocalDate(locale) ?: "",
            lastAirDate = entity.lastAirDate?.formatLocalDate(locale) ?: "",
            tagline = entity.tagline ?: "",
            overview = entity.overview ?: "",
            posterPath = entity.posterPath?.toImageUrl(),
            backdropPath = entity.backdropPath?.toImageUrl(),
            score = entity.score,
            numberOfSeasons = entity.numberOfSeasons,
            numberOfEpisodes = entity.numberOfEpisodes,
            inProduction = entity.inProduction,
        )
}