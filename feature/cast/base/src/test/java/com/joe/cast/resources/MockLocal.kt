package com.joe.cast.resources

import com.joe.cast.local.model.CastListLocalModel

object MockLocal {

    val model = CastListLocalModel(
        id = MockEntity.CAST_LIST_ID,
        cast = "[{\"id\":${MockEntity.ACTOR_ID_1},\"name\":\"${MockEntity.ACTOR_NAME_1}\",\"character\":\"${MockEntity.ACTOR_CHARACTER_1}\"},{\"id\":${MockEntity.ACTOR_ID_2},\"name\":\"${MockEntity.ACTOR_NAME_2}\",\"character\":\"${MockEntity.ACTOR_CHARACTER_2}\",\"profilePath\":\"${MockEntity.ACTOR_PROFILE_PATH_2}\"}]",
        crew = "[{\"id\":${MockEntity.CREW_PERSON_ID_1},\"name\":\"${MockEntity.CREW_NAME_1}\",\"job\":\"${MockEntity.CREW_JOB_1}\",\"profilePath\":\"${MockEntity.CREW_PROFILE_PATH_1}\"},{\"id\":${MockEntity.CREW_PERSON_ID_2},\"name\":\"${MockEntity.CREW_NAME_2}\",\"job\":\"${MockEntity.CREW_JOB_2}\"}]",
    )

}