package com.joe.movieDetails.domain.boundary

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.entity.MediaDetailsEntity
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Long) : Flow<Either<MediaDetailsEntity?, ErrorEntity?>>
}
