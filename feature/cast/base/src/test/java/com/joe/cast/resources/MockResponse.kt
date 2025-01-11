package com.joe.cast.resources

import com.joe.cast.data.response.ActorResponse
import com.joe.cast.data.response.CastListResponse
import com.joe.cast.data.response.CrewResponse

object MockResponse {

    val actor1 = ActorResponse(
        id = MockEntity.ACTOR_ID_1,
        name = MockEntity.ACTOR_NAME_1,
        character = MockEntity.ACTOR_CHARACTER_1,
        profilePath = null
    )

    val actor2 = ActorResponse(
        id = MockEntity.ACTOR_ID_2,
        name = MockEntity.ACTOR_NAME_2,
        character = MockEntity.ACTOR_CHARACTER_2,
        profilePath = MockEntity.ACTOR_PROFILE_PATH_2
    )

    val actorList = listOf(
        actor1,
        actor2
    )

    val crew1 = CrewResponse(
        id = MockEntity.CREW_PERSON_ID_1,
        name = MockEntity.CREW_NAME_1,
        job = MockEntity.CREW_JOB_1,
        profilePath = MockEntity.CREW_PROFILE_PATH_1
    )

    val crew2 = CrewResponse(
        id = MockEntity.CREW_PERSON_ID_2,
        name = MockEntity.CREW_NAME_2,
        job = MockEntity.CREW_JOB_2,
        profilePath = null
    )

    val crewList = listOf(
        crew1,
        crew2
    )

    val response = CastListResponse(
        id = MockEntity.CAST_LIST_ID,
        cast = actorList,
        crew = crewList
    )

}