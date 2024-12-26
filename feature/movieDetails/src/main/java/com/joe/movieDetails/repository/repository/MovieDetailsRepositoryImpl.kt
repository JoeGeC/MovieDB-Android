package com.joe.movieDetails.repository.repository

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.entity.MediaDetailsEntity
import com.joe.movieDetails.domain.boundary.MovieDetailsRepository
import com.joe.movieDetails.repository.boundary.MovieDetailsLocal
import com.joe.movieDetails.repository.boundary.MovieDetailsRemote
import com.joe.movieDetails.repository.converter.toEntity

class MovieDetailsRepositoryImpl(
    private val remote: MovieDetailsRemote,
    private val local: MovieDetailsLocal,
) : MovieDetailsRepository {
    
    override suspend fun getMovieDetails(movieId: Int): Either<MediaDetailsEntity?, ErrorEntity?> {
        val localResponse = local.getMovieDetails(movieId)
        return if (localResponse.isFailure) getFromRemote(movieId)
        else {
            localResponse.body?.toEntity()?.let { entity -> Either.Success(entity) }
                ?: getFromRemote(movieId)
        }
    }

    private suspend fun getFromRemote(movieId: Int): Either<MediaDetailsEntity?, ErrorEntity?> {
        val remoteResponse = remote.getMovieDetails(movieId)
        return if (remoteResponse.isFailure)
            Either.Failure(ErrorEntity(remoteResponse.errorBody?.statusMessage ?: "Remote fail"))
        else {
            remoteResponse.body?.toEntity()?.let{ entity -> Either.Success(entity) }
                ?: Either.Failure(ErrorEntity("Remote response is null"))
        }
    }

}