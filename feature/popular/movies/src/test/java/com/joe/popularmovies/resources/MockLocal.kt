package com.joe.popularmovies.resources

import com.joe.core.entity.Either
import com.joe.popularmovies.local.model.PopularMoviesLocalModel

object MockLocal {
    val currentTimeMillis = System.currentTimeMillis()
    val validTime = currentTimeMillis - 86400000L

    val model = PopularMoviesLocalModel(
        page = MockObjects.PAGE_1,
        movies = "[{\"id\":1,\"title\":\"The Lord of the Rings\",\"releaseDate\":\"1978-11-15\",\"posterPath\":\"/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg\",\"voteAverage\":6.589}]",
        totalPages = MockResponse.TOTAL_PAGES,
        cachedAt = currentTimeMillis
    )

    val success = Either.Success(model)
}