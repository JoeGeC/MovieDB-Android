package com.joe.moviesCast.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joe.cast.local.model.CastListLocalModel
import com.joe.moviesCast.local.dao.MovieCastDao

@Database(entities = [CastListLocalModel::class], version = 1, exportSchema = false)
abstract class MovieCastDatabase : RoomDatabase() {
    abstract fun movieCastDao(): MovieCastDao
}