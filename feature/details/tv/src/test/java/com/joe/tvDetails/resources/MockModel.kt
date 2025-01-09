package com.joe.tvDetails.resources

import com.joe.core.entity.Either
import com.joe.tvDetails.presentation.model.TvDetailsModel

object MockModel {
    const val FIRST_AIR_DATE = "17/09/2021"
    const val LAST_AIR_DATE = "26/12/2024"

    val model = TvDetailsModel(
        id = MockEntity.ID,
        name = MockEntity.NAME,
        firstAirDate = FIRST_AIR_DATE,
        lastAirDate = LAST_AIR_DATE,
        tagline = MockEntity.TAGLINE,
        overview = MockEntity.OVERVIEW,
        posterPath = "https://image.tmdb.org/t/p/original${MockEntity.POSTER_PATH}",
        score = MockEntity.SCORE,
        backdropPath = "https://image.tmdb.org/t/p/original${MockEntity.BACKDROP_PATH}",
        numberOfSeasons = MockEntity.NUMBER_OF_SEASONS,
        numberOfEpisodes = MockEntity.NUMBER_OF_EPISODES,
        inProduction = MockEntity.IN_PRODUCTION,
    )

    val nullSuccess = Either.Success(null)
}