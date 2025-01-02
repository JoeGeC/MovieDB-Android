package com.joe.popularmovies.resources

import com.joe.popularmovies.local.model.PopularMoviesLocalModel

object MockLocal {
    val currentTimeMillis = System.currentTimeMillis()
    val validTime = currentTimeMillis - 86400000L

    val model = PopularMoviesLocalModel(
        page = MockObjects.PAGE_1,
        movies = "",
        totalPages = MockResponse.TOTAL_PAGES,
        cachedAt = currentTimeMillis
    )
    
}