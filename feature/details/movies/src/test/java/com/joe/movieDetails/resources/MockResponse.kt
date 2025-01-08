package com.joe.movieDetails.resources

import com.joe.movieDetails.data.response.MovieDetailsResponse

object MockResponse {
    const val RELEASE_DATE = "1978-11-15"

    val response = MovieDetailsResponse(
        id = MockEntity.MOVIE_ID,
        title = MockEntity.TITLE_1,
        tagline = MockEntity.TAGLINE_1,
        overview = MockEntity.OVERVIEW_1,
        releaseDate = RELEASE_DATE,
        posterPath = MockEntity.POSTER_PATH_1,
        backdropPath = MockEntity.BACKDROP_PATH_1,
        voteAverage = MockEntity.SCORE_1,
    )
}