package com.joe.popularmovies.repository.response

import com.joe.popular.repository.response.PopularResponse

data class PopularMoviesResponse(
    override val page: Int?,
    override val results: List<MovieListItemResponse>?,
    override val totalPages: Int,
) : PopularResponse<MovieListItemResponse>