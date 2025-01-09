package com.joe.popular.resources

import com.joe.popular.presentation.model.MediaListItemModel
import com.joe.popular.presentation.model.MediaListModel

object MockModel {
    const val RELEASE_DATE_1 = "02/04/1999"

    val mediaListItem1 = MediaListItemModel(
        MockEntity.ID_1,
        MockEntity.TITLE_1,
        RELEASE_DATE_1,
        MockEntity.POSTER_PATH_1,
        MockEntity.SCORE_1,
    )
    const val RELEASE_DATE_2 = "05/01/2000"

    val mediaListItem2 = MediaListItemModel(
        MockEntity.ID_2,
        MockEntity.TITLE_2,
        RELEASE_DATE_2,
        MockEntity.POSTER_PATH_2,
        MockEntity.SCORE_2,
    )

    val mediaListItems1And2 = listOf(mediaListItem1, mediaListItem2)

    val mediaList = MediaListModel(
        MockEntity.PAGE_1,
        mediaListItems1And2,
        false,
    )


}