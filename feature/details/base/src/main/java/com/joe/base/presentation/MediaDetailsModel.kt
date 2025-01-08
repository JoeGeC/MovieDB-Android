package com.joe.base.presentation

abstract class MediaDetailsModel() {
    abstract val id: Int
    abstract val name: String
    abstract val tagline: String?
    abstract val overview: String?
    abstract val posterPath: String?
    abstract val backdropPath: String?
    abstract val score: Float?
}