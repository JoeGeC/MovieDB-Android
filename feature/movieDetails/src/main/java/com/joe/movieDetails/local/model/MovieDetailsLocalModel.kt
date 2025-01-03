package com.joe.movieDetails.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsLocalModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "tagline") val tagline: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String?,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "vote_average") val voteAverage: Float?,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?
)