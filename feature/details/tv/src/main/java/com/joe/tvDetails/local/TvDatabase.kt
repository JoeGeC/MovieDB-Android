package com.joe.tvDetails.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joe.tvDetails.local.dao.TvDetailsDao
import com.joe.tvDetails.local.model.TvDetailsLocalModel

@Database(entities = [TvDetailsLocalModel::class], version = 1, exportSchema = false)
abstract class TvDatabase : RoomDatabase() {
    abstract fun tvDetailsDao(): TvDetailsDao
}