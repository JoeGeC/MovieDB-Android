package com.joe.popularmovies

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.entity.MediaType
import com.joe.core.entity.MediaDetailsEntity
import com.joe.core.model.MediaDetailsModel
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.presentation.model.PopularMoviesModel
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate



object MockObjects {
    const val MOVIE_ID_1 = 1L
    const val TITLE_1 = "The Lord of the Rings"
    const val TAGLINE_1 = "Fantasy...beyond your imagination"
    const val OVERVIEW_1 =
        "The Fellowship of the Ring embark on a journey to destroy the One Ring and end Sauron's reign over Middle-earth."
    const val POSTER_PATH_1 = "https://image.tmdb.org/t/p/original/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg"
    const val SCORE_1 = 6.589f
    const val BACKDROP_PATH_1 = "https://image.tmdb.org/t/p/original/TXSxV23MWYkezZ3219gtgcSX6n.jpg"

    val model1 = MediaDetailsModel(
        id = MOVIE_ID_1,
        title = TITLE_1,
        releaseDate = "15/11/1978",
        tagline = TAGLINE_1,
        overview = OVERVIEW_1,
        posterPath = POSTER_PATH_1,
        score = SCORE_1,
        backdropPath = BACKDROP_PATH_1,
        type = MediaType.Movie
    )

    const val MOVIE_ID_2 = 2L
    const val TITLE_2 = "Movie 2"
    const val TAGLINE_2 = "Tagline 2"
    const val OVERVIEW_2 = "Overview 2"
    const val POSTER_PATH_2 = "https://image.tmdb.org/t/p/original/poster_path_2.jpg"
    const val SCORE_2 = 8.45f
    const val BACKDROP_PATH_2 = "https://image.tmdb.org/t/p/original/backdrop_path_2.jpg"

    val model2 = MediaDetailsModel(
        id = MOVIE_ID_2,
        title = TITLE_2,
        releaseDate = "16/12/1980",
        tagline = TAGLINE_2,
        overview = OVERVIEW_2,
        posterPath = POSTER_PATH_2,
        score = SCORE_2,
        backdropPath = BACKDROP_PATH_2,
        type = MediaType.Movie
    )

    val entity1 = MediaDetailsEntity(
        id = MOVIE_ID_1,
        title = TITLE_1,
        releaseDate = LocalDate.of(1978, 11, 15),
        tagline = TAGLINE_1,
        overview = OVERVIEW_1,
        posterPath = "/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg",
        score = SCORE_1,
        backdropPath = "/TXSxV23MWYkezZ3219gtgcSX6n.jpg",
        type = MediaType.Movie
    )

    val entity2 = MediaDetailsEntity(
        id = MOVIE_ID_2,
        title = TITLE_2,
        releaseDate = LocalDate.of(1980, 12, 16),
        tagline = TAGLINE_2,
        overview = OVERVIEW_2,
        posterPath = "/poster_path_2.jpg",
        score = SCORE_2,
        backdropPath = "/backdrop_path_2.jpg",
        type = MediaType.Movie
    )

    val entityList1 = listOf(entity1)
    val entityList2 = listOf(entity2)

    val popularMoviesEntity1 = PopularMoviesEntity(1, entityList1, false)
    val popularMoviesEntity2 = PopularMoviesEntity(2, entityList2, true)

    val modelList1 = listOf(model1)
    val modelList2 = listOf(model1, model2)

    val popularMoviesModel1 = PopularMoviesModel(1, modelList1, false)
    val popularMoviesModel2 = PopularMoviesModel(2, modelList2, true)

    val success1 = Either.Success(popularMoviesEntity1)
    val success2 = Either.Success(popularMoviesEntity2)
    val successFlow1 = flowOf(success1)
    val successFlow2 = flowOf(success2)

    const val ERROR_MESSAGE = "Error"
    val failure = Either.Failure(ErrorEntity(ERROR_MESSAGE))
    val failureFlow = flowOf(failure)
}