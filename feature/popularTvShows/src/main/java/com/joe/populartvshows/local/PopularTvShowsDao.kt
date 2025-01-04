package com.joe.populartvshows.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joe.populartvshows.local.model.PopularTvShowsLocalModel

@Dao
interface PopularTvShowsDao {
    @Query("SELECT * FROM popular_tv_shows WHERE page = :page AND cachedAt > :validTime LIMIT 1")
    fun getByPage(page: Int, validTime: Long): PopularTvShowsLocalModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg popularMovies: PopularTvShowsLocalModel)
}