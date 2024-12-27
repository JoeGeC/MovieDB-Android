package com.joe.movieDetails.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDetailsDao {
    @Query("SELECT * FROM movie_details")
    fun getAll(): List<MovieDetailsLocalModel>

    @Query("SELECT * FROM movie_details WHERE id IN (:movieIds)")
    fun loadAllByIds(movieIds: IntArray): List<MovieDetailsLocalModel>

    @Query("SELECT * FROM movie_details WHERE title LIKE :title LIMIT 1")
    fun findByTitle(title: String): MovieDetailsLocalModel?

    @Query("SELECT * FROM movie_details WHERE id = :id LIMIT 1")
    fun getById(id: Int): MovieDetailsLocalModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movieDetails: MovieDetailsLocalModel)
}