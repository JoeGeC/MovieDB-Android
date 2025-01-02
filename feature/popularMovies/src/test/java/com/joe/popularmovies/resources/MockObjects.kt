package com.joe.popularmovies.resources

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popularmovies.domain.entity.MovieListItemEntity
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.presentation.model.MovieListItemModel
import com.joe.popularmovies.presentation.model.PopularMoviesModel
import java.time.LocalDate

object MockObjects {
    const val PAGE_1 = 1
    const val PAGE_2 = 2
    const val MOVIE_ID_1 = 1
    const val TITLE_1 = "The Lord of the Rings"
    const val SCORE_1 = 6.589f
    const val POSTER_PATH_1 = "https://image.tmdb.org/t/p/original/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg"

    val model1 = MovieListItemModel(
        id = MOVIE_ID_1,
        title = TITLE_1,
        releaseDate = "15/11/1978",
        posterPath = POSTER_PATH_1,
        score = SCORE_1,
    )

    const val MOVIE_ID_2 = 2
    const val TITLE_2 = "Movie 2"
    const val POSTER_PATH_2 = "https://image.tmdb.org/t/p/original/poster_path_2.jpg"
    const val SCORE_2 = 8.45f

    val model2 = MovieListItemModel(
        id = MOVIE_ID_2,
        title = TITLE_2,
        releaseDate = "16/12/1980",
        posterPath = POSTER_PATH_2,
        score = SCORE_2,
    )

    val entity1 = MovieListItemEntity(
        id = MOVIE_ID_1,
        title = TITLE_1,
        releaseDate = LocalDate.of(1978, 11, 15),
        posterPath = "/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg",
        score = SCORE_1,
    )

    val entity2 = MovieListItemEntity(
        id = MOVIE_ID_2,
        title = TITLE_2,
        releaseDate = LocalDate.of(1980, 12, 16),
        posterPath = "/poster_path_2.jpg",
        score = SCORE_2,
    )

    val entityList1 = listOf(entity1)
    val entityList2 = listOf(entity2)

    val popularMoviesEntity1 = PopularMoviesEntity(1, entityList1, false)
    val popularMoviesEntity2 = PopularMoviesEntity(2, entityList2, true)

    val modelList1 = listOf(model1)
    val modelList2 = listOf(model1, model2)

    val popularMoviesModel1 = PopularMoviesModel(PAGE_1, modelList1, false)
    val popularMoviesModel2 = PopularMoviesModel(PAGE_2, modelList2, true)

    val success1 = Either.Success(popularMoviesEntity1)
    val success2 = Either.Success(popularMoviesEntity2)
    val nullSuccess = Either.Success(null)

    const val ERROR_MESSAGE = "Error Message"
    val failure = Either.Failure(ErrorEntity(ERROR_MESSAGE))
}