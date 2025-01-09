package com.joe.populartvshows.data.response

import com.joe.popular.repository.response.PopularResponse

data class PopularTvShowsResponse(
    override val page: Int?,
    override val results: List<TvShowListItemResponse>?,
    override val totalPages: Int,
) : PopularResponse<TvShowListItemResponse>