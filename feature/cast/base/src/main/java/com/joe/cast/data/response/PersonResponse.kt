package com.joe.cast.data.response

data class PersonResponse(
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String?
)