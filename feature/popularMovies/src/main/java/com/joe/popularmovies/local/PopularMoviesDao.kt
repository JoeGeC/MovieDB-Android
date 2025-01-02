package com.joe.popularmovies.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joe.popularmovies.local.model.PopularMoviesLocalModel

@Dao
interface PopularMoviesDao {
    @Query("SELECT * FROM popular_movies WHERE page = :page AND cachedAt > :validTime LIMIT 1")
    fun getByPage(page: Int, validTime: Long): PopularMoviesLocalModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg popularMovies: PopularMoviesLocalModel)
}