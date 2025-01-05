package com.joe.movieDetails.local

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.movieDetails.local.converter.toLocal
import com.joe.movieDetails.local.converter.toResponse
import com.joe.movieDetails.repository.boundary.MovieDetailsLocal
import com.joe.movieDetails.repository.response.MovieDetailsResponse

class MovieDetailsLocalImpl(private val database: MovieDatabase) : MovieDetailsLocal {
    override suspend fun insert(movieDetailsResponse: MovieDetailsResponse) {
        try {
            database.movieDetailsDao().insertAll(movieDetailsResponse.toLocal())
        } catch (_: Exception){}
    }

    override suspend fun getMovieDetails(movieId: Int): Either<MovieDetailsResponse?, ErrorResponse?> {
        val response = database.movieDetailsDao().getById(movieId)?.toResponse()
            ?: return Either.Failure(ErrorResponse("Null local response"))
        return Either.Success(response)
    }

}

