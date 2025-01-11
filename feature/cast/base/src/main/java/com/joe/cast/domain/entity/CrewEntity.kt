package com.joe.cast.domain.entity

data class CrewEntity(
    val id: Int,
    val name: String,
    val job: String?,
    val profilePath: String?
)