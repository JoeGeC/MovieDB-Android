package com.joe.populartvshows.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joe.popular.local.database.PaginatedDatabase
import com.joe.populartvshows.local.dao.PopularTvShowsDao
import com.joe.populartvshows.local.model.PopularTvShowsLocalModel

@Database(entities = [PopularTvShowsLocalModel::class], version = 1, exportSchema = false)
abstract class PopularTvShowsDatabase : RoomDatabase(), PaginatedDatabase {
    abstract fun popularTvShowsDao(): PopularTvShowsDao
}