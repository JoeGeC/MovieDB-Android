package com.joe.popularmovies.local

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.popularmovies.local.model.PopularMoviesLocalModel
import com.joe.popularmovies.repository.boundary.PopularMoviesLocal

class PopularMoviesLocalImpl(private val database: PopularMoviesDatabase) : PopularMoviesLocal {
    private val cacheValidDuration24Hours = 86400000L

    override fun get(page: Int): Either<PopularMoviesLocalModel?, ErrorResponse> {
        val validTime = System.currentTimeMillis() - cacheValidDuration24Hours
        val response = database.popularMoviesDao().getByPage(page, validTime)
            ?: return Either.Failure(ErrorResponse("Cache expired or no data available"))
        return Either.Success(response)
    }

    override fun insert(popularMovies: PopularMoviesLocalModel?) {
        try {
            if(popularMovies == null) return
            val updatedModel = popularMovies.copy(cachedAt = System.currentTimeMillis())
            database.popularMoviesDao().insertAll(updatedModel)
        } catch (_: Exception){}
    }

}
