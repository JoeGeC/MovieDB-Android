package com.joe.popular.resources

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.domain.entity.MediaListItemEntity
import java.time.LocalDate

object MockEntity {
    const val PAGE_1 = 1
    const val PAGE_2 = 2

    const val ID_1 = 123
    const val TITLE_1 = "Media item 1"
    val RELEASE_DATE_1 = LocalDate.of(1999, 4, 2)
    const val POSTER_PATH_1 = "/poster_path_1.png"
    const val SCORE_1 = 90f

    val mediaListItem1 = MediaListItemEntity(
        ID_1,
        TITLE_1,
        RELEASE_DATE_1,
        POSTER_PATH_1,
        SCORE_1,
    )

    const val ID_2 = 234
    const val TITLE_2 = "Media item 2"
    val RELEASE_DATE_2 = LocalDate.of(2000, 1, 5)
    const val POSTER_PATH_2 = "/poster_path_2.png"
    const val SCORE_2 = 50f

    val mediaListItem2 = MediaListItemEntity(
        ID_2,
        TITLE_2,
        RELEASE_DATE_2,
        POSTER_PATH_2,
        SCORE_2,
    )

    val mediaListItems1And2 = listOf(mediaListItem1, mediaListItem2)

    val mediaList1And2 = MediaListEntity(
        PAGE_1,
        mediaListItems1And2,
        false,
    )

    val mediaList1 = MediaListEntity(
        PAGE_1,
        mediaListItems1And2,
        false,
    )

    val mediaList2 = MediaListEntity(
        PAGE_1,
        mediaListItems1And2,
        true,
    )

    val success1 = Either.Success(mediaList1)
    val success2 = Either.Success(mediaList2)
    val success1And2 = Either.Success(mediaList1And2)
    const val USE_CASE_ERROR_MESSAGE = "Error loading data"
    const val ERROR_MESSAGE = "Error loading data"
    val failure = Either.Failure(ErrorEntity(USE_CASE_ERROR_MESSAGE))
}