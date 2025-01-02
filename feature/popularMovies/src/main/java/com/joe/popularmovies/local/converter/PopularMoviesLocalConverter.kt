package com.joe.popularmovies.local.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.joe.popularmovies.domain.entity.MovieListItemEntity
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.local.model.PopularMoviesLocalModel
import com.joe.popularmovies.repository.response.PopularMoviesResponse

fun PopularMoviesResponse.toLocal(): PopularMoviesLocalModel? = PopularMoviesLocalModel(
    page = this.page ?: throw NullPointerException(),
    movies = Gson().toJson(this.results),
    totalPages = this.totalPages,
    cachedAt = System.currentTimeMillis()
)

fun PopularMoviesLocalModel?.toEntity(): PopularMoviesEntity? = PopularMoviesEntity(
    page = this?.page ?: throw java.lang.NullPointerException(),
    movies = Gson().fromJson(this.movies, object: TypeToken<List<MovieListItemEntity>>() {}.type),
    isFinalPage = this.totalPages <= this.page,
)