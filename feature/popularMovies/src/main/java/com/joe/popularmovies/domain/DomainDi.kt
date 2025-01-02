package com.joe.popularmovies.domain

import com.joe.popularmovies.domain.boundary.PopularMoviesRepository
import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainDi {

    @Provides
    fun provideMovieDetailsUseCase(
        repository: PopularMoviesRepository
    ): PopularMoviesUseCase = PopularMoviesUseCase(repository)

}