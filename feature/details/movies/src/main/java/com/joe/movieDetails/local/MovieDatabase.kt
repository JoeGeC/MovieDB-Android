package com.joe.movieDetails.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joe.movieDetails.local.model.MovieDetailsLocalModel

@Database(entities = [MovieDetailsLocalModel::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDetailsDao(): MovieDetailsDao
}