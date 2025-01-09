package com.joe.movieDetails.resources

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.movieDetails.domain.MovieDetailsEntity
import com.joe.movieDetails.resources.MockModel.ERROR_MESSAGE
import java.time.LocalDate

object MockEntity {
    const val MOVIE_ID = 123
    const val TITLE_1 = "The Lord of the Rings"
    const val TAGLINE_1 = "Fantasy...beyond your imagination"
    const val OVERVIEW_1 = "The Fellowship of the Ring embark on a journey to destroy the One Ring and end Sauron's reign over Middle-earth."
    const val POSTER_PATH_1 = "/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg"
    const val BACKDROP_PATH_1 = "/TXSxV23MWYkezZ3219gtgcSX6n.jpg"
    const val SCORE_1 = 6.589f

    val entity = MovieDetailsEntity(
        id = MOVIE_ID,
        title = TITLE_1,
        releaseDate = LocalDate.of(1978, 11, 15),
        tagline = TAGLINE_1,
        overview = OVERVIEW_1,
        posterPath = POSTER_PATH_1,
        score = SCORE_1,
        backdropPath = BACKDROP_PATH_1,
    )

    val failure = Either.Failure(ErrorEntity(ERROR_MESSAGE))
    val success = Either.Success(entity)
}