package com.joe.cast.presentation.model

data class CastListModel(
    val id: Int,
    val cast: List<ActorModel>,
    val crew: List<CrewModel>
)