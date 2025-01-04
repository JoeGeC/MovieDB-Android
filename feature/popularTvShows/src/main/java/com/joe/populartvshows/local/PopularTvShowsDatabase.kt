package com.joe.populartvshows.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joe.populartvshows.local.model.PopularTvShowsLocalModel

@Database(entities = [PopularTvShowsLocalModel::class], version = 1, exportSchema = false)
abstract class PopularTvShowsDatabase : RoomDatabase() {
    abstract fun popularTvShowsDao(): PopularTvShowsDao
}