package com.joe.cast.domain.entity

data class CastListEntity(
    val id: Int,
    val cast: List<ActorEntity>,
    val crew: List<CrewEntity>
)