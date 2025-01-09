package com.joe.tvDetails.resources

import com.joe.tvDetails.data.response.TvDetailsResponse

object MockResponseModel {
    const val FIRST_AIR_DATE = "2021-09-17"
    const val LAST_AIR_DATE = "2024-12-26"

    val response = TvDetailsResponse(
        id = MockEntity.ID,
        name = MockEntity.NAME,
        tagline = MockEntity.TAGLINE,
        overview = MockEntity.OVERVIEW,
        firstAirDate = FIRST_AIR_DATE,
        lastAirDate = LAST_AIR_DATE,
        posterPath = MockEntity.POSTER_PATH,
        backdropPath = MockEntity.BACKDROP_PATH,
        voteAverage = MockEntity.SCORE,
        numberOfEpisodes = MockEntity.NUMBER_OF_EPISODES,
        numberOfSeasons = MockEntity.NUMBER_OF_SEASONS,
        inProduction = MockEntity.IN_PRODUCTION,
    )
}