package com.joe.movieDetails.domain

import com.joe.movieDetails.domain.boundary.MovieDetailsRepository
import com.joe.movieDetails.domain.usecase.MovieDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainDi {

    @Provides
    fun provideMovieDetailsUseCase(
        repository: MovieDetailsRepository
    ): MovieDetailsUseCase = MovieDetailsUseCase(repository)

}