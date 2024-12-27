package com.joe.movieDetails.repository.repository

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.entity.MediaDetailsEntity
import com.joe.movieDetails.domain.boundary.MovieDetailsRepository
import com.joe.movieDetails.repository.boundary.MovieDetailsLocal
import com.joe.movieDetails.repository.boundary.MovieDetailsRemote
import com.joe.movieDetails.repository.converter.toEntity
import com.joe.movieDetails.repository.response.MovieDetailsResponse

class MovieDetailsRepositoryImpl(
    private val remote: MovieDetailsRemote,
    private val local: MovieDetailsLocal,
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): Either<MediaDetailsEntity?, ErrorEntity?> =
        local.getMovieDetails(movieId).fold(
            onSuccess = { response ->
                response?.toEntity()?.let {
                    Either.Success(it)
                } ?: fetchFromRemote(movieId)
            },
            onFailure = { fetchFromRemote(movieId) }
        )

    private suspend fun fetchFromRemote(movieId: Int): Either<MediaDetailsEntity?, ErrorEntity?> =
        remote.getMovieDetails(movieId).fold(
            onSuccess = { response -> handleRemoteSuccess(response) },
            onFailure = { error ->
                Either.Failure(ErrorEntity(error?.statusMessage ?: "Remote fetch failed"))
            }
        )

    private suspend fun handleRemoteSuccess(response: MovieDetailsResponse?): Either<MediaDetailsEntity?, ErrorEntity?> =
        response?.toEntity()?.let { entity ->
            local.insert(response)
            Either.Success(entity)
        } ?: Either.Failure(ErrorEntity("Remote conversion failed"))

}