package com.joe.movieDetails.presentation.converter

import com.joe.base.presentation.converter.DetailsPresentationConverter
import com.joe.movieDetails.domain.MovieDetailsEntity
import com.joe.movieDetails.presentation.model.MovieDetailsModel
import com.joe.presentation.formatter.formatLocalDate
import com.joe.presentation.formatter.toImageUrl
import java.util.Locale

class MovieDetailsPresentationConverter(
    private val locale: Locale = Locale.getDefault()
): DetailsPresentationConverter<MovieDetailsModel, MovieDetailsEntity> {

    override fun entityToModel(entity: MovieDetailsEntity): MovieDetailsModel =
        MovieDetailsModel(
            id = entity.id,
            name = entity.title,
            releaseDate = entity.releaseDate?.formatLocalDate(locale) ?: "",
            tagline = entity.tagline ?: "",
            overview = entity.overview ?: "",
            posterPath = entity.posterPath?.toImageUrl(),
            score = entity.score,
            backdropPath = entity.backdropPath?.toImageUrl(),
        )
}