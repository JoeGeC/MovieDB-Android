package com.joe.tvCast.resources

object MockJson {

    val SUCCESS = "{\n" +
            "  \"cast\": [\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"gender\": 0,\n" +
            "      \"id\": ${MockEntity.ACTOR_ID_1},\n" +
            "      \"known_for_department\": \"Acting\",\n" +
            "      \"name\": \"${MockEntity.ACTOR_NAME_1}\",\n" +
            "      \"original_name\": \"Rana Walker\",\n" +
            "      \"popularity\": 0.003,\n" +
            "      \"profile_path\": null,\n" +
            "      \"character\": \"${MockEntity.ACTOR_CHARACTER_1}\",\n" +
            "      \"credit_id\": \"52533ba219c295794006f319\",\n" +
            "      \"order\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"gender\": 1,\n" +
            "      \"id\": ${MockEntity.ACTOR_ID_2},\n" +
            "      \"known_for_department\": \"Acting\",\n" +
            "      \"name\": \"${MockEntity.ACTOR_NAME_2}\",\n" +
            "      \"original_name\": \"Iyanla Vanzant\",\n" +
            "      \"popularity\": 7.528,\n" +
            "      \"profile_path\": \"${MockEntity.ACTOR_PROFILE_PATH_2}\",\n" +
            "      \"character\": \"${MockEntity.ACTOR_CHARACTER_2}\",\n" +
            "      \"credit_id\": \"52533ba419c295794006f331\",\n" +
            "      \"order\": 1\n" +
            "    }\n" +
            "  ],\n" +
            "  \"crew\": [\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"gender\": 0,\n" +
            "      \"id\": ${MockEntity.CREW_PERSON_ID_1},\n" +
            "      \"known_for_department\": \"Production\",\n" +
            "      \"name\": \"${MockEntity.CREW_NAME_1}\",\n" +
            "      \"original_name\": \"Stephanie Tomasky\",\n" +
            "      \"popularity\": 0.001,\n" +
            "      \"profile_path\": \"${MockEntity.CREW_PROFILE_PATH_1}\",\n" +
            "      \"credit_id\": \"52533bbc19c295794006f5a3\",\n" +
            "      \"department\": \"Production\",\n" +
            "      \"job\": \"${MockEntity.CREW_JOB_1}\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"gender\": 0,\n" +
            "      \"id\": ${MockEntity.CREW_PERSON_ID_2},\n" +
            "      \"known_for_department\": \"Production\",\n" +
            "      \"name\": \"${MockEntity.CREW_NAME_2}\",\n" +
            "      \"original_name\": \"Cliff Grant\",\n" +
            "      \"popularity\": 0.001,\n" +
            "      \"profile_path\": null,\n" +
            "      \"credit_id\": \"52533bc919c295794006f6db\",\n" +
            "      \"department\": \"Production\",\n" +
            "      \"job\": \"${MockEntity.CREW_JOB_2}\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"id\": ${MockEntity.CAST_LIST_ID}\n" +
            "}"


    const val ERROR_CODE = 400
    const val ERROR_MESSAGE = "Invalid id: The pre-requisite id is invalid or not found."
    const val ERROR_JSON = "{\n" +
            "  \"success\": false,\n" +
            "  \"status_code\": $ERROR_CODE,\n" +
            "  \"status_message\": \"$ERROR_MESSAGE\"\n" +
            "}"
}