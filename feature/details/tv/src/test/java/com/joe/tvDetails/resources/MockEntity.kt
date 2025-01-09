package com.joe.tvDetails.resources

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.tvDetails.domain.TvDetailsEntity
import com.joe.tvDetails.resources.MockJson.ERROR_MESSAGE
import java.time.LocalDate

object MockEntity {
    const val ID = 93405
    const val NAME = "Squid Game"
    const val TAGLINE = "45.6 billion won is child's play."
    const val OVERVIEW =
        "Hundreds of cash-strapped players accept a strange invitation to compete in children's games. Inside, a tempting prize awaits — with deadly high stakes."
    const val POSTER_PATH = "/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg"
    const val SCORE = 7.842f
    const val BACKDROP_PATH = "/ukAmSyNdtWduHZfm27R2EOsguKt.jpg"
    const val NUMBER_OF_EPISODES = 22
    const val NUMBER_OF_SEASONS = 3
    const val IN_PRODUCTION = true

    val entity = TvDetailsEntity(
        id = ID,
        name = NAME,
        firstAirDate = LocalDate.of(2021, 9, 17),
        lastAirDate = LocalDate.of(2024, 12, 26),
        tagline = TAGLINE,
        overview = OVERVIEW,
        posterPath = POSTER_PATH,
        score = SCORE,
        backdropPath = BACKDROP_PATH,
        numberOfEpisodes = NUMBER_OF_EPISODES,
        numberOfSeasons = NUMBER_OF_SEASONS,
        inProduction = IN_PRODUCTION,
    )

    val failure = Either.Failure(ErrorEntity(ERROR_MESSAGE))
    val success = Either.Success(entity)
}