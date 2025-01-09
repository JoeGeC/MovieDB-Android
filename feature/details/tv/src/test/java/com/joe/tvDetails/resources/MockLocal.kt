package com.joe.tvDetails.resources

import com.joe.tvDetails.local.model.TvDetailsLocalModel

object MockLocal {

    val model = TvDetailsLocalModel(
        id = MockEntity.ID,
        name = MockEntity.NAME,
        tagline = MockEntity.TAGLINE,
        overview = MockEntity.OVERVIEW,
        firstAirDate = MockResponseModel.FIRST_AIR_DATE,
        lastAirDate = MockResponseModel.LAST_AIR_DATE,
        posterPath = MockEntity.POSTER_PATH,
        voteAverage = MockEntity.SCORE,
        backdropPath = MockEntity.BACKDROP_PATH,
        numberOfEpisodes = MockEntity.NUMBER_OF_EPISODES,
        numberOfSeasons = MockEntity.NUMBER_OF_SEASONS,
        inProduction = MockEntity.IN_PRODUCTION
    )

}