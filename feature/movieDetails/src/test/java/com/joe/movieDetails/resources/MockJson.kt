package com.joe.movieDetails.resources

object MockJson {
    const val MOVIE_ID = 123
    const val MOVIE_TITLE = "The Lord of the Rings"
    const val MOVIE_TAGLINE = "Fantasy...beyond your imagination"
    const val MOVIE_OVERVIEW =
        "The Fellowship of the Ring embark on a journey to destroy the One Ring and end Sauron's reign over Middle-earth."
    const val MOVIE_RELEASE_DATE = "1978-11-15"
    const val MOVIE_POSTER_PATH = "/liW0mjvTyLs7UCumaHhx3PpU4VT.jpg"
    const val MOVIE_VOTE_AVERAGE = 6.589f
    const val MOVIE_BACKDROP_PATH = "/TXSxV23MWYkezZ3219gtgcSX6n.jpg"

    const val SUCCESS = "{\n" +
            "  \"adult\": false,\n" +
            "  \"backdrop_path\": \"$MOVIE_BACKDROP_PATH\",\n" +
            "  \"belongs_to_collection\": null,\n" +
            "  \"budget\": 4000000,\n" +
            "  \"genres\": [\n" +
            "    {\n" +
            "      \"id\": 12,\n" +
            "      \"name\": \"Adventure\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 16,\n" +
            "      \"name\": \"Animation\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 14,\n" +
            "      \"name\": \"Fantasy\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"homepage\": \"\",\n" +
            "  \"id\": $MOVIE_ID,\n" +
            "  \"imdb_id\": \"tt0077869\",\n" +
            "  \"origin_country\": [\n" +
            "    \"GB\",\n" +
            "    \"US\"\n" +
            "  ],\n" +
            "  \"original_language\": \"en\",\n" +
            "  \"original_title\": \"The Lord of the Rings\",\n" +
            "  \"overview\": \"$MOVIE_OVERVIEW\",\n" +
            "  \"popularity\": 29.825,\n" +
            "  \"poster_path\": \"$MOVIE_POSTER_PATH\",\n" +
            "  \"production_companies\": [\n" +
            "    {\n" +
            "      \"id\": 286,\n" +
            "      \"logo_path\": null,\n" +
            "      \"name\": \"Fantasy Films\",\n" +
            "      \"origin_country\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 4921,\n" +
            "      \"logo_path\": null,\n" +
            "      \"name\": \"Bakshi Productions\",\n" +
            "      \"origin_country\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 141322,\n" +
            "      \"logo_path\": null,\n" +
            "      \"name\": \"Saul Zaentz Film Productions\",\n" +
            "      \"origin_country\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 60,\n" +
            "      \"logo_path\": \"/4YldLvCWQL9VIAHQ2Fu3Ffkh9Si.png\",\n" +
            "      \"name\": \"United Artists\",\n" +
            "      \"origin_country\": \"US\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"production_countries\": [\n" +
            "    {\n" +
            "      \"iso_3166_1\": \"GB\",\n" +
            "      \"name\": \"United Kingdom\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"iso_3166_1\": \"US\",\n" +
            "      \"name\": \"United States of America\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"release_date\": \"$MOVIE_RELEASE_DATE\",\n" +
            "  \"revenue\": 30500000,\n" +
            "  \"runtime\": 132,\n" +
            "  \"spoken_languages\": [\n" +
            "    {\n" +
            "      \"english_name\": \"English\",\n" +
            "      \"iso_639_1\": \"en\",\n" +
            "      \"name\": \"English\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"Released\",\n" +
            "  \"tagline\": \"$MOVIE_TAGLINE\",\n" +
            "  \"title\": \"$MOVIE_TITLE\",\n" +
            "  \"video\": false,\n" +
            "  \"vote_average\": $MOVIE_VOTE_AVERAGE,\n" +
            "  \"vote_count\": 914\n" +
            "}"

    const val ERROR_MESSAGE = "Error Message"
    const val ERROR_CODE = 400

    const val ERROR_JSON = "{\n" +
            "  \"success\": false,\n" +
            "  \"status_code\": $ERROR_CODE,\n" +
            "  \"status_message\": \"$ERROR_MESSAGE\"\n" +
            "}"

}