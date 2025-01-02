package com.joe.popularmovies.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joe.popularmovies.local.model.PopularMoviesLocalModel

@Database(entities = [PopularMoviesLocalModel::class], version = 1, exportSchema = false)
abstract class PopularMoviesDatabase : RoomDatabase() {
    abstract fun popularMoviesDao(): PopularMoviesDao
}