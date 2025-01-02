package com.joe.popularmovies.local.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class PopularMoviesLocalModel(
    @PrimaryKey val page: Int,
    @ColumnInfo(name = "movies") val movies: List<MovieListItemLocalModel>,
    @ColumnInfo(name = "isFinalPage") val isFinalPage: Boolean

)