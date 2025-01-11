package com.joe.cast.presentation.model

data class ActorModel(
    override val id: Int,
    override val name: String,
    val character: String?,
    override val profilePath: String?
): Person {
    override fun getListSubtitle(): String? = character
}
