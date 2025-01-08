package com.joe.tvDetails.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_details")
data class TvDetailsLocalModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "tagline") val tagline: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "first_air_date") val firstAirDate: String?,
    @ColumnInfo(name = "last_air_date") val lastAirDate: String?,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
    @ColumnInfo(name = "vote_average") val voteAverage: Float?,
    @ColumnInfo(name = "number_of_seasons") val numberOfSeasons: Int?,
    @ColumnInfo(name = "number_of_episodes") val numberOfEpisodes: Int?,
    @ColumnInfo(name = "in_production") val inProduction: Boolean?,
)