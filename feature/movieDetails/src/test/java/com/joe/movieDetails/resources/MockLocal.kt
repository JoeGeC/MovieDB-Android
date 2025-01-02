package com.joe.movieDetails.resources

import com.joe.movieDetails.local.model.MovieDetailsLocalModel

object MockLocal {

    val model = MovieDetailsLocalModel(
        id = MockEntity.MOVIE_ID,
        title = MockEntity.TITLE_1,
        tagline = MockEntity.TAGLINE_1,
        overview = MockEntity.OVERVIEW_1,
        releaseDate = MockResponse.RELEASE_DATE,
        posterPath = MockEntity.POSTER_PATH_1,
        voteAverage = MockEntity.SCORE_1,
        backdropPath = MockEntity.BACKDROP_PATH_1
    )

}