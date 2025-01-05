package com.joe.popularmovies.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joe.popular.local.model.TimeLimitCacheModel

@Entity(tableName = "popular_movies")
data class PopularMoviesLocalModel(
    @PrimaryKey val page: Int,
    @ColumnInfo(name = "movies") val movies: String?,
    @ColumnInfo(name = "totalPages") val totalPages: Int,
    @ColumnInfo(name = "cachedAt") override val cachedAt: Long
): TimeLimitCacheModel {

    override fun copyWith(cachedAt: Long): TimeLimitCacheModel = copy(cachedAt = cachedAt)

    override fun equals(other: Any?): Boolean =
        other is PopularMoviesLocalModel &&
                page == other.page &&
                movies == other.movies &&
                totalPages == other.totalPages

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + page
        result = 31 * result + (movies?.hashCode() ?: 0)
        result = 31 * result + totalPages
        result = 31 * result + cachedAt.hashCode()
        return result
    }

}