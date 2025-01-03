package com.joe.popularmovies.domain.boundary

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popularmovies.domain.entity.PopularMoviesEntity

interface PopularMoviesRepository {
    suspend fun getPopularMovies(page: Int) : Either<PopularMoviesEntity?, ErrorEntity?>
}
