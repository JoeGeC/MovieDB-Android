package com.joe.popularmovies.resources

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.popularmovies.repository.response.MovieListItemResponse
import com.joe.popularmovies.repository.response.PopularMoviesResponse

object MockResponse {
    const val POSTER_PATH_1 = "/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg"
    const val TOTAL_PAGES = 2

    val movieDetails = MovieListItemResponse(
        MockObjects.MOVIE_ID_1,
        MockObjects.TITLE_1,
        "1978-11-15",
        POSTER_PATH_1,
        MockObjects.SCORE_1,
    )

    val popularMovies = PopularMoviesResponse(
        MockObjects.PAGE_1,
        listOf(movieDetails),
        TOTAL_PAGES
    )

    const val ERROR_MESSAGE = "Error Message"

    val success = Either.Success(popularMovies)
    val failure = Either.Failure(ErrorResponse(ERROR_MESSAGE))
}