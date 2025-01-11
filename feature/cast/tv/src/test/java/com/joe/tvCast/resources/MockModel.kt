package com.joe.tvCast.resources

import com.joe.cast.presentation.model.ActorModel
import com.joe.cast.presentation.model.CastListModel
import com.joe.cast.presentation.model.CrewModel
import com.joe.tvCast.resources.MockEntity.ACTOR_CHARACTER_1
import com.joe.tvCast.resources.MockEntity.ACTOR_CHARACTER_2
import com.joe.tvCast.resources.MockEntity.ACTOR_ID_1
import com.joe.tvCast.resources.MockEntity.ACTOR_ID_2
import com.joe.tvCast.resources.MockEntity.ACTOR_NAME_1
import com.joe.tvCast.resources.MockEntity.ACTOR_NAME_2
import com.joe.tvCast.resources.MockEntity.ACTOR_PROFILE_PATH_2
import com.joe.tvCast.resources.MockEntity.CAST_LIST_ID
import com.joe.tvCast.resources.MockEntity.CREW_JOB_1
import com.joe.tvCast.resources.MockEntity.CREW_JOB_2
import com.joe.tvCast.resources.MockEntity.CREW_NAME_1
import com.joe.tvCast.resources.MockEntity.CREW_NAME_2
import com.joe.tvCast.resources.MockEntity.CREW_PERSON_ID_1
import com.joe.tvCast.resources.MockEntity.CREW_PERSON_ID_2
import com.joe.tvCast.resources.MockEntity.CREW_PROFILE_PATH_1

object MockModel {

    val actor1 = ActorModel(
        id = ACTOR_ID_1,
        name = ACTOR_NAME_1,
        character = ACTOR_CHARACTER_1,
        profilePath = null
    )
    val actor2 = ActorModel(
        id = ACTOR_ID_2,
        name = ACTOR_NAME_2,
        character = ACTOR_CHARACTER_2,
        profilePath = "https://image.tmdb.org/t/p/original$ACTOR_PROFILE_PATH_2"
    )

    val actorList = listOf(
        actor1,
        actor2
    )
    val crewPerson1 = CrewModel(
        id = CREW_PERSON_ID_1,
        name = CREW_NAME_1,
        profilePath = "https://image.tmdb.org/t/p/original$CREW_PROFILE_PATH_1",
        job = CREW_JOB_1
    )

    val crewPerson2 = CrewModel(
        id = CREW_PERSON_ID_2,
        name = CREW_NAME_2,
        profilePath = null,
        job = CREW_JOB_2
    )

    val crewList = listOf(
        crewPerson1,
        crewPerson2
    )

    val model = CastListModel(
        id = CAST_LIST_ID,
        cast = actorList,
        crew = crewList
    )

}