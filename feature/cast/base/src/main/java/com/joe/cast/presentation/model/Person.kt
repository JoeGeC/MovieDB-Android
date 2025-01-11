package com.joe.cast.presentation.model

interface Person {
    val id: Int
    val name: String
    val profilePath: String?
    fun getListSubtitle(): String?
}