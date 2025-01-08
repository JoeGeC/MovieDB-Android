package com.joe.movieDetails.repository.converter

import com.joe.base.repository.converter.DetailsRepositoryConverter
import com.joe.core.entity.toLocalDateOrNull
import com.joe.movieDetails.domain.MovieDetailsEntity
import com.joe.movieDetails.local.model.MovieDetailsLocalModel
import com.joe.movieDetails.data.response.MovieDetailsResponse

class MovieDetailsRepositoryConverter :
    DetailsRepositoryConverter<MovieDetailsEntity, MovieDetailsLocalModel, MovieDetailsResponse> {

    override fun localToEntity(model: MovieDetailsLocalModel?): MovieDetailsEntity =
        MovieDetailsEntity(
            id = model?.id ?: throw NullPointerException(),
            title = model.title ?: throw NullPointerException(),
            tagline = model.tagline,
            overview = model.overview,
            releaseDate = model.releaseDate?.toLocalDateOrNull(),
            posterPath = model.posterPath,
            score = model.voteAverage,
            backdropPath = model.backdropPath,
        )

    override fun remoteToLocal(response: MovieDetailsResponse?): MovieDetailsLocalModel? =
        MovieDetailsLocalModel(
            id = response?.id ?: throw NullPointerException(),
            title = response.title ?: throw NullPointerException(),
            tagline = response.tagline ?: throw NullPointerException(),
            overview = response.overview ?: throw NullPointerException(),
            releaseDate = response.releaseDate ?: throw NullPointerException(),
            posterPath = response.posterPath ?: throw NullPointerException(),
            voteAverage = response.voteAverage ?: throw NullPointerException(),
            backdropPath = response.backdropPath ?: throw NullPointerException()
        )

    override fun remoteToEntity(response: MovieDetailsResponse?): MovieDetailsEntity =
        MovieDetailsEntity(
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