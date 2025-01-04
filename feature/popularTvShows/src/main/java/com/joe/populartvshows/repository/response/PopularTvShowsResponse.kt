package com.joe.populartvshows.repository.response

import com.joe.popular.repository.response.PaginatedResponse

data class PopularTvShowsResponse(
    override val page: Int?,
    override val results: List<TvShowListItemResponse>?,
    override val totalPages: Int,
) : PaginatedResponse<TvShowListItemResponse>