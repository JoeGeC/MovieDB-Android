package com.joe.popularmovies.presentation.converter

import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.presentation.model.MovieListItemModel
import com.joe.popularmovies.presentation.model.PopularMoviesModel
import java.util.Locale

fun PopularMoviesEntity.toModel(
    previousMovies: List<MovieListItemModel>,
    locale: Locale = Locale.getDefault()
): PopularMoviesModel =
    PopularMoviesModel(
        page = this.page,
        movies = combineMovies(previousMovies, convertMovies(locale)),
        isFinalPage = this.isFinalPage,
    )

private fun PopularMoviesEntity.combineMovies(
    previousMovies: List<MovieListItemModel>,
    thisMovies: List<MovieListItemModel>,
): List<MovieListItemModel> = (previousMovies + thisMovies).distinctBy { it.id }

private fun PopularMoviesEntity.convertMovies(locale: Locale): List<MovieListItemModel> =
    this.movies.map { movie -> movie.toModel(locale) }