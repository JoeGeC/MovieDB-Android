package com.joe.tvCast.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joe.cast.local.model.CastListLocalModel

@Dao
interface TvCastDao {
    @Query("SELECT * FROM media_cast WHERE id = :id LIMIT 1")
    fun getById(id: Int): CastListLocalModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg tvDetails: CastListLocalModel)
}