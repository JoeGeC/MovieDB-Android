package com.joe.popularmovies.repository.boundary

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.popularmovies.local.model.PopularMoviesLocalModel

interface PopularMoviesLocal {
    fun get(page: Int): Either<PopularMoviesLocalModel?, ErrorResponse>
    fun insert(response: PopularMoviesLocalModel?)
}