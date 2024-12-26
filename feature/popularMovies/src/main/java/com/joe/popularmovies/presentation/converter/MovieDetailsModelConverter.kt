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
        movies = combineMovies(previousMovies, convertMovies(locale)),
        isFinalPage = this.isFinalPage,
    )

private fun PopularMoviesEntity.combineMovies(
    previousMovies: List<MediaDetailsModel>,
    thisMovies: List<MediaDetailsModel>,
): List<MediaDetailsModel> = (previousMovies + thisMovies).distinctBy { it.id }

private fun PopularMoviesEntity.convertMovies(locale: Locale): List<MediaDetailsModel> =
    this.movies.map { movie -> movie.toModel(locale) }