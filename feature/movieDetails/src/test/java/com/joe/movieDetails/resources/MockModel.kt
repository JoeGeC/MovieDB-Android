package com.joe.movieDetails.resources

import com.joe.core.entity.Either
import com.joe.core.entity.MediaType
import com.joe.core.model.MediaDetailsModel
import com.joe.movieDetails.resources.MockEntity.MOVIE_ID

object MockModel {
    val model = MediaDetailsModel(
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

    val nullSuccess = Either.Success(null)
    const val ERROR_MESSAGE = "Error"
}