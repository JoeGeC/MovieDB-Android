package com.joe.popularmovies.resources

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.popularmovies.repository.response.MovieListItemResponse
import com.joe.popularmovies.repository.response.PopularMoviesResponse

object MockResponse {
    const val POSTER_PATH_1 = "/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg"

    val movieDetails = MovieListItemResponse(
        MockObjects.MOVIE_ID_1,
        MockObjects.TITLE_1,
        "1978-11-15",
        POSTER_PATH_1,
        MockObjects.SCORE_1,
    )

    val movieDetailsWithNullData = MovieListItemResponse(
        MockObjects.MOVIE_ID_1,
        null,
        null,
        null,
        null,
    )

    val emptyPopularMovies = PopularMoviesResponse(
        MockObjects.PAGE_1,
        listOf<MovieListItemResponse>(),
        false
    )

    val popularMovies = PopularMoviesResponse(
        MockObjects.PAGE_1,
        listOf(movieDetails),
        false
    )

    val popularMoviesWithNullMovieData = PopularMoviesResponse(
        MockObjects.PAGE_1,
        listOf(movieDetails, movieDetailsWithNullData),
        false
    )

    val popularMoviesWithNullData = PopularMoviesResponse(
        null,
        listOf(movieDetails),
        false
    )

    const val ERROR_MESSAGE = "Error Message"

    val emptySuccess = Either.Success(emptyPopularMovies)
    val nullMovieDataSuccess = Either.Success(popularMoviesWithNullMovieData)
    val nullDataSuccess = Either.Success(popularMoviesWithNullData)
    val success = Either.Success(popularMovies)
    val failure = Either.Failure(ErrorResponse(ERROR_MESSAGE))
}