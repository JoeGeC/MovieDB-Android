package com.joe.cast.presentation.model

data class CrewModel(
    override val id: Int,
    override val name: String,
    val job: String?,
    override val profilePath: String?
): Person {
    override fun getListSubtitle(): String? = job
}