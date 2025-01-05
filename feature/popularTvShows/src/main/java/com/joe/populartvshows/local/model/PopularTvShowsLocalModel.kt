package com.joe.populartvshows.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joe.popular.local.model.TimeLimitCacheModel

@Entity(tableName = "popular_tv_shows")
data class PopularTvShowsLocalModel(
    @PrimaryKey val page: Int,
    @ColumnInfo(name = "shows") val shows: String?,
    @ColumnInfo(name = "totalPages") val totalPages: Int,
    @ColumnInfo(name = "cachedAt") override val cachedAt: Long
): TimeLimitCacheModel {

    override fun copy(cachedAt: Long): TimeLimitCacheModel = copy(cachedAt = cachedAt)

    override fun equals(other: Any?): Boolean =
        other is PopularTvShowsLocalModel &&
                page == other.page &&
                shows == other.shows &&
                totalPages == other.totalPages

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + page
        result = 31 * result + (shows?.hashCode() ?: 0)
        result = 31 * result + totalPages
        result = 31 * result + cachedAt.hashCode()
        return result
    }

}