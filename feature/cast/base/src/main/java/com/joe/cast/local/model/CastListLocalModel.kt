package com.joe.cast.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_cast")
data class CastListLocalModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "cast") val cast: String,
    @ColumnInfo(name = "crew") val crew: String
)