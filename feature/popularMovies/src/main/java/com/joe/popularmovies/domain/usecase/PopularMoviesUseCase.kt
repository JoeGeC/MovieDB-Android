package com.joe.popularmovies.domain.usecase

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popularmovies.domain.boundary.PopularMoviesRepository
import com.joe.popularmovies.domain.entity.PopularMoviesEntity

class PopularMoviesUseCase(
    private val repository: PopularMoviesRepository
) {
    suspend fun getPopularMovies(page: Int): Either<PopularMoviesEntity?, ErrorEntity?> =
        repository.getPopularMovies(page)
}