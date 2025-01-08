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
            title = entity.title,
            releaseDate = entity.releaseDate?.formatLocalDate(locale) ?: "",
            tagline = entity.tagline ?: "",
            overview = entity.overview ?: "",
            posterPath = entity.posterPath?.toImageUrl(),
            score = entity.score,
            backdropPath = entity.backdropPath?.toImageUrl(),
        )
}