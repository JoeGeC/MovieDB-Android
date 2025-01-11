package com.joe.tvCast.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joe.cast.local.model.CastListLocalModel
import com.joe.tvCast.local.dao.TvCastDao

@Database(entities = [CastListLocalModel::class], version = 1, exportSchema = false)
abstract class TvCastDatabase : RoomDatabase() {
    abstract fun tvCastDao(): TvCastDao
}