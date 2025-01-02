package com.joe.popularmovies.local.converter

import com.joe.popularmovies.local.model.PopularMoviesLocalModel
import com.joe.popularmovies.repository.response.PopularMoviesResponse


fun PopularMoviesResponse.toLocal(): PopularMoviesLocalModel? = PopularMoviesLocalModel(
    page = this.page ?: throw NullPointerException(),
    movies = this.movies?.map { it.toLocal() } ?: throw NullPointerException(),
    isFinalPage = this.isFinalPage,
)

fun PopularMoviesLocalModel.toResponse(): PopularMoviesResponse? = PopularMoviesResponse(
    page = this.page,
    movies = this.movies.map { it.toResponse() },
    isFinalPage = this.isFinalPage,
)
