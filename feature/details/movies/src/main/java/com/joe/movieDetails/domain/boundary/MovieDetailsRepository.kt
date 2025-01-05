package com.joe.movieDetails.domain.boundary

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.entity.MediaDetailsEntity

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int) : Either<MediaDetailsEntity?, ErrorEntity?>
}
