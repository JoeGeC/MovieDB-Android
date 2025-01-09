package com.joe.populartvshows.resources

object MockJson {
    const val PAGE_1 = 1
    const val TOTAL_PAGES = 2

    const val ID_1 = 93405
    const val TITLE_1 = "Squid Game"
    const val FIRST_AIR_DATE_1 = "2021-09-17"
    const val POSTER_PATH_1 = "/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg"
    const val SCORE_1 = 7.8f

    const val ID_2 = 558449
    const val TITLE_2 = "Binnelanders"
    const val FIRST_AIR_DATE_2 = "2005-10-13"
    const val POSTER_PATH_2 = "/3bzECfllho8PphdYujLUIuhncJD.jpg"
    const val SCORE_2 = 5.6f

    const val SUCCESS = "{\n" +
            "  \"page\": $PAGE_1,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/ukAmSyNdtWduHZfm27R2EOsguKt.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        10759,\n" +
            "        9648,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 93405,\n" +
            "      \"origin_country\": [\n" +
            "        \"KR\"\n" +
            "      ],\n" +
            "      \"original_language\": \"ko\",\n" +
            "      \"original_name\": \"오징어 게임\",\n" +
            "      \"overview\": \"Hundreds of cash-strapped players accept a strange invitation to compete in children's games. Inside, a tempting prize awaits — with deadly high stakes.\",\n" +
            "      \"popularity\": 9549.701,\n" +
            "      \"poster_path\": \"$POSTER_PATH_1\",\n" +
            "      \"first_air_date\": \"$FIRST_AIR_DATE_1\",\n" +
            "      \"name\": \"$TITLE_1\",\n" +
            "      \"vote_average\": $SCORE_1,\n" +
            "      \"vote_count\": 14800\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/mu3lEhGovyhKHPJzb7HNYtZUCDT.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        10766\n" +
            "      ],\n" +
            "      \"id\": $ID_2,\n" +
            "      \"origin_country\": [\n" +
            "        \"ZA\"\n" +
            "      ],\n" +
            "      \"original_language\": \"af\",\n" +
            "      \"original_name\": \"$TITLE_2\",\n" +
            "      \"overview\": \"A South African Afrikaans soap opera. It is set in and around the fictional private hospital, Binneland Kliniek, in Pretoria, and the storyline follows the trials, trauma and tribulations of the staff and patients of the hospital.\",\n" +
            "      \"popularity\": 4586.659,\n" +
            "      \"poster_path\": \"$POSTER_PATH_2\",\n" +
            "      \"first_air_date\": \"$FIRST_AIR_DATE_2\",\n" +
            "      \"name\": \"$TITLE_2\",\n" +
            "      \"vote_average\": $SCORE_2,\n" +
            "      \"vote_count\": 80\n" +
            "    }" +
            "  ],\n" +
            "  \"total_pages\": $TOTAL_PAGES,\n" +
            "  \"total_results\": 189232\n" +
            "}"
}