package com.joe.cast.data.response

data class CastListResponse(
    val id: Int,
    val cast: List<PersonResponse>,
    val crew: List<PersonResponse>
) {
}