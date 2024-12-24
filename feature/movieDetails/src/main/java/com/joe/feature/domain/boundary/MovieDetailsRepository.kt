package com.joe.feature.domain.boundary

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.feature.domain.entity.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Long) : Flow<Either<MovieDetailsEntity?, ErrorEntity?>>
}
