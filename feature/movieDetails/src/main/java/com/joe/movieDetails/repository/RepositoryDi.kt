package com.joe.movieDetails.repository

import com.joe.movieDetails.domain.boundary.MovieDetailsRepository
import com.joe.movieDetails.repository.boundary.MovieDetailsLocal
import com.joe.movieDetails.repository.boundary.MovieDetailsRemote
import com.joe.movieDetails.repository.repository.MovieDetailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryDi {

    @Provides
    fun provideMovieDetailsRepository(
        remote: MovieDetailsRemote,
        local: MovieDetailsLocal
    ): MovieDetailsRepository = MovieDetailsRepositoryImpl(remote, local)

}