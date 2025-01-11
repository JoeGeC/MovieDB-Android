package com.joe.tvCast.resources

import com.joe.cast.domain.entity.CastListEntity
import com.joe.cast.domain.entity.ActorEntity
import com.joe.cast.domain.entity.CrewEntity
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity

object MockEntity {
    const val CAST_LIST_ID = 1
    const val ACTOR_ID_1 = 11
    const val ACTOR_ID_2 = 12
    const val ACTOR_NAME_1 = "Actor Person 1"
    const val ACTOR_CHARACTER_1 = "Character 1"
    const val ACTOR_NAME_2 = "Actor Person 2"
    const val ACTOR_CHARACTER_2 = "Character 2"
    const val ACTOR_PROFILE_PATH_2 = "/castPersonPath2.jpg"


    val actor1 = ActorEntity(
        id = ACTOR_ID_1,
        name = ACTOR_NAME_1,
        character = ACTOR_CHARACTER_1,
        profilePath = null
    )
    val actor2 = ActorEntity(
        id = ACTOR_ID_2,
        name = ACTOR_NAME_2,
        character = ACTOR_CHARACTER_2,
        profilePath = ACTOR_PROFILE_PATH_2
    )

    val castList = listOf(
        actor1,
        actor2
    )
    const val CREW_PERSON_ID_1 = 21
    const val CREW_PERSON_ID_2 = 22
    const val CREW_NAME_1 = "Crew Person 1"
    const val CREW_NAME_2 = "Crew Person 2"
    const val CREW_PROFILE_PATH_1 = "/crew_path1.jpg"
    const val CREW_JOB_1 = "Producer"
    const val CREW_JOB_2 = "Artist"

    val crewPerson1 = CrewEntity(
        id = CREW_PERSON_ID_1,
        name = CREW_NAME_1,
        profilePath = CREW_PROFILE_PATH_1,
        job = CREW_JOB_1
    )

    val crewPerson2 = CrewEntity(
        id = CREW_PERSON_ID_2,
        name = CREW_NAME_2,
        profilePath = null,
        job = CREW_JOB_2
    )

    val crewList = listOf(
        crewPerson1,
        crewPerson2
    )

    val entity = CastListEntity(
        id = CAST_LIST_ID,
        cast = castList,
        crew = crewList
    )

    const val ERROR_MESSAGE = "Error Message"
    val failure = Either.Failure(ErrorEntity(ERROR_MESSAGE))
    val nullSuccess = Either.Success(null)
    val success = Either.Success(entity)

}