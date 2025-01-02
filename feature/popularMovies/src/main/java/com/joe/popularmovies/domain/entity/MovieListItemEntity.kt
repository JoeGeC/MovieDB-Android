package com.joe.popularmovies.domain.entity

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class MovieListItemEntity(
    val id: Int,
    val title: String?,
    val releaseDate: LocalDate?,
    val posterPath: String?,
    @SerializedName("voteAverage") val score: Float,
)