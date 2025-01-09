package com.joe.tvDetails.resources

import com.joe.tvDetails.resources.MockEntity.BACKDROP_PATH
import com.joe.tvDetails.resources.MockEntity.ID
import com.joe.tvDetails.resources.MockEntity.NAME
import com.joe.tvDetails.resources.MockEntity.NUMBER_OF_EPISODES
import com.joe.tvDetails.resources.MockEntity.NUMBER_OF_SEASONS
import com.joe.tvDetails.resources.MockEntity.OVERVIEW
import com.joe.tvDetails.resources.MockEntity.POSTER_PATH
import com.joe.tvDetails.resources.MockEntity.SCORE
import com.joe.tvDetails.resources.MockEntity.TAGLINE
import com.joe.tvDetails.resources.MockResponseModel.FIRST_AIR_DATE
import com.joe.tvDetails.resources.MockResponseModel.LAST_AIR_DATE

object MockJson {
    const val SUCCESS = "{\n" +
            "  \"adult\": false,\n" +
            "  \"backdrop_path\": \"$BACKDROP_PATH\",\n" +
            "  \"created_by\": [\n" +
            "    {\n" +
            "      \"id\": 1294471,\n" +
            "      \"credit_id\": \"677a62f8830a8f4cc766aacd\",\n" +
            "      \"name\": \"Hwang Dong-hyuk\",\n" +
            "      \"original_name\": \"황동혁\",\n" +
            "      \"gender\": 2,\n" +
            "      \"profile_path\": \"/xyr3b04ayyJtA5ZN3L0Af10WKIR.jpg\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"episode_run_time\": [],\n" +
            "  \"first_air_date\": \"$FIRST_AIR_DATE\",\n" +
            "  \"genres\": [\n" +
            "    {\n" +
            "      \"id\": 10759,\n" +
            "      \"name\": \"Action & Adventure\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 9648,\n" +
            "      \"name\": \"Mystery\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 18,\n" +
            "      \"name\": \"Drama\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"homepage\": \"https://www.netflix.com/title/81040344\",\n" +
            "  \"id\": $ID,\n" +
            "  \"in_production\": true,\n" +
            "  \"languages\": [\n" +
            "    \"en\",\n" +
            "    \"ko\",\n" +
            "    \"ur\"\n" +
            "  ],\n" +
            "  \"last_air_date\": \"$LAST_AIR_DATE\",\n" +
            "  \"last_episode_to_air\": {\n" +
            "    \"id\": 5747919,\n" +
            "    \"name\": \"Friend or Foe\",\n" +
            "    \"overview\": \"The remaining players strategize on how to survive the night. Gi-hun proposes a risky plan — but he will need trustworthy allies to carry it out.\",\n" +
            "    \"vote_average\": 8.346,\n" +
            "    \"vote_count\": 52,\n" +
            "    \"air_date\": \"2024-12-26\",\n" +
            "    \"episode_number\": 7,\n" +
            "    \"episode_type\": \"finale\",\n" +
            "    \"production_code\": \"\",\n" +
            "    \"runtime\": 61,\n" +
            "    \"season_number\": 2,\n" +
            "    \"show_id\": 93405,\n" +
            "    \"still_path\": \"/tO8hO3jWp4JveXmBbPikd4IQbss.jpg\"\n" +
            "  },\n" +
            "  \"name\": \"$NAME\",\n" +
            "  \"next_episode_to_air\": {\n" +
            "    \"id\": 5884550,\n" +
            "    \"name\": \"Episode 1\",\n" +
            "    \"overview\": \"\",\n" +
            "    \"vote_average\": 0,\n" +
            "    \"vote_count\": 0,\n" +
            "    \"air_date\": \"2025-06-27\",\n" +
            "    \"episode_number\": 1,\n" +
            "    \"episode_type\": \"standard\",\n" +
            "    \"production_code\": \"\",\n" +
            "    \"runtime\": null,\n" +
            "    \"season_number\": 3,\n" +
            "    \"show_id\": 93405,\n" +
            "    \"still_path\": null\n" +
            "  },\n" +
            "  \"networks\": [\n" +
            "    {\n" +
            "      \"id\": 213,\n" +
            "      \"logo_path\": \"/wwemzKWzjKYJFfCeiB57q3r4Bcm.png\",\n" +
            "      \"name\": \"Netflix\",\n" +
            "      \"origin_country\": \"\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"number_of_episodes\": $NUMBER_OF_EPISODES,\n" +
            "  \"number_of_seasons\": $NUMBER_OF_SEASONS,\n" +
            "  \"origin_country\": [\n" +
            "    \"KR\"\n" +
            "  ],\n" +
            "  \"original_language\": \"ko\",\n" +
            "  \"original_name\": \"오징어 게임\",\n" +
            "  \"overview\": \"$OVERVIEW\",\n" +
            "  \"popularity\": 9549.701,\n" +
            "  \"poster_path\": \"$POSTER_PATH\",\n" +
            "  \"production_companies\": [\n" +
            "    {\n" +
            "      \"id\": 112647,\n" +
            "      \"logo_path\": null,\n" +
            "      \"name\": \"Siren Pictures\",\n" +
            "      \"origin_country\": \"KR\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 203249,\n" +
            "      \"logo_path\": null,\n" +
            "      \"name\": \"Firstman Studio\",\n" +
            "      \"origin_country\": \"KR\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"production_countries\": [\n" +
            "    {\n" +
            "      \"iso_3166_1\": \"KR\",\n" +
            "      \"name\": \"South Korea\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"seasons\": [\n" +
            "    {\n" +
            "      \"air_date\": \"2021-09-17\",\n" +
            "      \"episode_count\": 9,\n" +
            "      \"id\": 131977,\n" +
            "      \"name\": \"Season 1\",\n" +
            "      \"overview\": \"Hundreds of cash-strapped players accept a strange invitation to compete in children's games. Inside, a tempting prize awaits — with deadly high stakes.\",\n" +
            "      \"poster_path\": \"/jlbrV1Kl4Y8pWXu12SppebRs7On.jpg\",\n" +
            "      \"season_number\": 1,\n" +
            "      \"vote_average\": 8.3\n" +
            "    },\n" +
            "    {\n" +
            "      \"air_date\": \"2024-12-26\",\n" +
            "      \"episode_count\": 7,\n" +
            "      \"id\": 287516,\n" +
            "      \"name\": \"Season 2\",\n" +
            "      \"overview\": \"Ready to run for your lives? Player 456 returns for more heart-pounding children's games, facing deadly new challenges — but armed with a hidden agenda.\",\n" +
            "      \"poster_path\": \"/caq0z9C2vvKdDhGe1EX6nerswV5.jpg\",\n" +
            "      \"season_number\": 2,\n" +
            "      \"vote_average\": 8.5\n" +
            "    },\n" +
            "    {\n" +
            "      \"air_date\": \"2025-06-27\",\n" +
            "      \"episode_count\": 6,\n" +
            "      \"id\": 435948,\n" +
            "      \"name\": \"Season 3\",\n" +
            "      \"overview\": \"\",\n" +
            "      \"poster_path\": \"/8l2KDsy0rdb98BRiRbMsbgASRan.jpg\",\n" +
            "      \"season_number\": 3,\n" +
            "      \"vote_average\": 0\n" +
            "    }\n" +
            "  ],\n" +
            "  \"spoken_languages\": [\n" +
            "    {\n" +
            "      \"english_name\": \"English\",\n" +
            "      \"iso_639_1\": \"en\",\n" +
            "      \"name\": \"English\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"english_name\": \"Korean\",\n" +
            "      \"iso_639_1\": \"ko\",\n" +
            "      \"name\": \"한국어/조선말\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"english_name\": \"Urdu\",\n" +
            "      \"iso_639_1\": \"ur\",\n" +
            "      \"name\": \"اردو\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"Returning Series\",\n" +
            "  \"tagline\": \"$TAGLINE\",\n" +
            "  \"type\": \"Scripted\",\n" +
            "  \"vote_average\": $SCORE,\n" +
            "  \"vote_count\": 14800\n" +
            "}"

    const val ERROR_MESSAGE = "Error Message"
    const val ERROR_CODE = 400

    const val ERROR_JSON = "{\n" +
            "  \"success\": false,\n" +
            "  \"status_code\": $ERROR_CODE,\n" +
            "  \"status_message\": \"$ERROR_MESSAGE\"\n" +
            "}"

}