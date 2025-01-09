package com.joe.populartvshows.resources

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.domain.entity.MediaListItemEntity
import java.time.LocalDate

object MockEntity {
    const val PAGE_1 = 1
    const val MOVIE_ID_1 = 1
    const val TITLE_1 = "The Lord of the Rings"
    const val SCORE_1 = 6.589f

    val entity1 = MediaListItemEntity(
        id = MOVIE_ID_1,
        title = TITLE_1,
        releaseDate = LocalDate.of(1978, 11, 15),
        posterPath = "/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg",
        score = SCORE_1,
    )

    val entityList1 = listOf(entity1)

    val popularMoviesEntity1 = MediaListEntity(1, entityList1, false)

    val success1 = Either.Success(popularMoviesEntity1)

    const val ERROR_MESSAGE = "Error loading data"
    val failure = Either.Failure(ErrorEntity(ERROR_MESSAGE))
}