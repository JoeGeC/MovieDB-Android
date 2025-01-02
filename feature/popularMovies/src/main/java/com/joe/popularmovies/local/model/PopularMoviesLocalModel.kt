package com.joe.popularmovies.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movies")
data class PopularMoviesLocalModel(
    @PrimaryKey val page: Int,
    @ColumnInfo(name = "movies") val movies: String?,
    @ColumnInfo(name = "totalPages") val totalPages: Int
)