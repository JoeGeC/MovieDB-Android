package com.joe.popularmovies.resources

object MockJson {
    const val PAGE_1 = 1
    const val TOTAL_PAGES = 2

    const val MOVIE_ID_1 = 1156593
    const val MOVIE_TITLE_1 = "Your Fault"
    const val MOVIE_RELEASE_DATE_1 = "2024-12-26"
    const val MOVIE_POSTER_PATH_1 = "/1sQA7lfcF9yUyoLYC0e6Zo3jmxE.jpg"
    const val MOVIE_SCORE_1 = 7.271f

    const val MOVIE_ID_2 = 558449
    const val MOVIE_TITLE_2 = "Gladiator II"
    const val MOVIE_RELEASE_DATE_2 = "2024-11-05"
    const val MOVIE_POSTER_PATH_2 = "/2cxhvwyEwRlysAmRH4iodkvo0z5.jpg"
    const val MOVIE_SCORE_2 = 6.775f

    const val SUCCESS = "{\n" +
            "  \"page\": $PAGE_1,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/6qld2YxAO9gdEblo0rsEb8BcYKO.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        10749,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": $MOVIE_ID_1,\n" +
            "      \"original_language\": \"es\",\n" +
            "      \"original_title\": \"Culpa tuya\",\n" +
            "      \"overview\": \"The love between Noah and Nick seems unwavering despite their parents' attempts to separate them. But his job and her entry into college open up their lives to new relationships that will shake the foundations of both their relationship and the Leister family itself.\",\n" +
            "      \"popularity\": 5111.721,\n" +
            "      \"poster_path\": \"$MOVIE_POSTER_PATH_1\",\n" +
            "      \"release_date\": \"$MOVIE_RELEASE_DATE_1\",\n" +
            "      \"title\": \"$MOVIE_TITLE_1\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": $MOVIE_SCORE_1,\n" +
            "      \"vote_count\": 479\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/euYIwmwkmz95mnXvufEmbL6ovhZ.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        12,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": $MOVIE_ID_2,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Gladiator II\",\n" +
            "      \"overview\": \"Years after witnessing the death of the revered hero Maximus at the hands of his uncle, Lucius is forced to enter the Colosseum after his home is conquered by the tyrannical Emperors who now lead Rome with an iron fist. With rage in his heart and the future of the Empire at stake, Lucius must look to his past to find strength and honor to return the glory of Rome to its people.\",\n" +
            "      \"popularity\": 5438.718,\n" +
            "      \"poster_path\": \"$MOVIE_POSTER_PATH_2\",\n" +
            "      \"release_date\": \"$MOVIE_RELEASE_DATE_2\",\n" +
            "      \"title\": \"$MOVIE_TITLE_2\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": $MOVIE_SCORE_2,\n" +
            "      \"vote_count\": 1775\n" +
            "    }\n" +
            "  ],\n" +
            "  \"total_pages\": $TOTAL_PAGES,\n" +
            "  \"total_results\": 958997\n" +
            "}"
}