package com.joe.tvDetails.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joe.tvDetails.local.model.TvDetailsLocalModel

@Dao
interface TvDetailsDao {
    @Query("SELECT * FROM tv_details WHERE id = :id LIMIT 1")
    fun getById(id: Int): TvDetailsLocalModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg tvDetails: TvDetailsLocalModel)
}