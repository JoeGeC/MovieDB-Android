package com.joe.popularmovies.repository.boundary

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.popularmovies.repository.response.PopularMoviesResponse

interface PopularMoviesRemote {
    fun getPopularMovies(page: Int): Either<PopularMoviesResponse?, ErrorResponse?>
}