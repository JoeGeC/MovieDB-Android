package com.joe.popularmovies.repository.converter

import com.joe.popularmovies.domain.entity.MovieListItemEntity
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.repository.response.MovieListItemResponse
import com.joe.popularmovies.repository.response.PopularMoviesResponse

fun PopularMoviesResponse.toEntity() = PopularMoviesEntity(
    page = this.page ?: throw NullPointerException(),
    movies = this.results?.mapNotNull { tryConvertMovie(it) } ?: throw IllegalStateException(),
    isFinalPage = this.totalPages <= this.page
)

private fun tryConvertMovie(response: MovieListItemResponse): MovieListItemEntity? = try {
    response.toEntity()
} catch (_: Exception) {
    null
}