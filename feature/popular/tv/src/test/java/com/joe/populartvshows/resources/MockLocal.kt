package com.joe.populartvshows.resources

import com.joe.core.entity.Either
import com.joe.populartvshows.local.model.PopularTvShowsLocalModel

object MockLocal {
    val currentTimeMillis = System.currentTimeMillis()
    val validTime = currentTimeMillis - 86400000L

    val model = PopularTvShowsLocalModel(
        page = MockEntity.PAGE_1,
        shows = "[{\"id\":1,\"name\":\"The Lord of the Rings\",\"firstAirDate\":\"1978-11-15\",\"posterPath\":\"/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg\",\"voteAverage\":6.589}]",
        totalPages = MockResponse.TOTAL_PAGES,
        cachedAt = currentTimeMillis
    )

    val success = Either.Success(model)
}