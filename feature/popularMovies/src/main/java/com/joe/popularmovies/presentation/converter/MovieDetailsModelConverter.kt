package com.joe.popularmovies.presentation.converter

import com.joe.core.converter.toModel
import com.joe.core.model.MediaDetailsModel
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.presentation.model.PopularMoviesModel
import java.util.Locale

fun PopularMoviesEntity.toModel(
    previousMovies: List<MediaDetailsModel>,
    locale: Locale = Locale.getDefault()
): PopularMoviesModel =
    PopularMoviesModel(
        page = this.page,
        movies = previousMovies + this.movies.map { movie -> movie.toModel(locale) },
        isFinalPage = this.isFinalPage,
    )