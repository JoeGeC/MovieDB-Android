package com.joe.popularmovies.resources

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.popularmovies.data.response.MovieListItemResponse
import com.joe.popularmovies.data.response.PopularMoviesResponse

object MockResponse {
    const val POSTER_PATH_1 = "/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg"
    const val TOTAL_PAGES = 2

    val movieDetails = MovieListItemResponse(
        MockEntity.MOVIE_ID_1,
        MockEntity.TITLE_1,
        "1978-11-15",
        POSTER_PATH_1,
        MockEntity.SCORE_1,
    )

    val popularMovies = PopularMoviesResponse(
        MockEntity.PAGE_1,
        listOf(movieDetails),
        TOTAL_PAGES
    )

    val success = Either.Success(popularMovies)
    val failure = Either.Failure(ErrorResponse(MockEntity.ERROR_MESSAGE))
}