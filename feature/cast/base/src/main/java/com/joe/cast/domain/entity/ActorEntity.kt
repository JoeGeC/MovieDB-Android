package com.joe.cast.domain.entity

data class ActorEntity(
    val id: Int,
    val name: String,
    val character: String?,
    val profilePath: String?
)