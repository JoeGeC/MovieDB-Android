package com.joe.populartvshows.local

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.popular.local.PaginatedLocal
import com.joe.populartvshows.local.model.PopularTvShowsLocalModel

class PopularTvShowsLocalImpl(private val database: PopularTvShowsDatabase) : PaginatedLocal<PopularTvShowsLocalModel> {
    private val cacheValidDuration24Hours = 86400000L

    override fun get(page: Int): Either<PopularTvShowsLocalModel?, ErrorResponse> {
        val validTime = System.currentTimeMillis() - cacheValidDuration24Hours
        val response = database.popularTvShowsDao().getByPage(page, validTime)
            ?: return Either.Failure(ErrorResponse("Cache expired or no data available"))
        return Either.Success(response)
    }

    override fun insert(popularTvShows: PopularTvShowsLocalModel?) {
        try {
            if(popularTvShows == null) return
            val updatedModel = popularTvShows.copy(cachedAt = System.currentTimeMillis())
            database.popularTvShowsDao().insertAll(updatedModel)
        } catch (_: Exception){}
    }

}
