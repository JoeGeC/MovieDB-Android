package com.joe.feature.presentation

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.feature.domain.entity.MediaType
import com.joe.feature.domain.entity.MovieDetailsEntity
import com.joe.feature.presentation.model.MovieDetailsModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate

object MockObjects {
    const val MOVIE_ID = 1L

    val model = MovieDetailsModel(
        id = MOVIE_ID,
        title = "The Lord of the Rings",
        releaseDate = "15/11/1978",
        tagline = "Fantasy...beyond your imagination",
        overview = "The Fellowship of the Ring embark on a journey to destroy the One Ring and end Sauron's reign over Middle-earth.",
        posterPath = "https://image.tmdb.org/t/p/original/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg",
        score = 6.589f,
        backdropPath = "https://image.tmdb.org/t/p/original/TXSxV23MWYkezZ3219gtgcSX6n.jpg",
        type = MediaType.Movie
    )

    val entity = MovieDetailsEntity(
        id = MOVIE_ID,
        title = "The Lord of the Rings",
        releaseDate = LocalDate.of(1978, 11, 15),
        tagline = "Fantasy...beyond your imagination",
        overview = "The Fellowship of the Ring embark on a journey to destroy the One Ring and end Sauron's reign over Middle-earth.",
        posterPath = "/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg",
        score = 6.589f,
        backdropPath = "/TXSxV23MWYkezZ3219gtgcSX6n.jpg",
        type = MediaType.Movie
    )

    val success = Either.Success(entity)
    val successFlow = flowOf(success)
    const val ERROR_MESSAGE = "Error"
    val failure = Either.Failure(ErrorEntity(ERROR_MESSAGE))
    val failureFlow = flowOf(failure)
}