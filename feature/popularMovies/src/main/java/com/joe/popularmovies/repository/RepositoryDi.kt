package com.joe.popularmovies.repository

import com.joe.popularmovies.domain.boundary.PopularMoviesRepository
import com.joe.popularmovies.repository.boundary.PopularMoviesLocal
import com.joe.popularmovies.repository.boundary.PopularMoviesRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryDi {

    @Provides
    fun providePopularMoviesRepository(
        remote: PopularMoviesRemote,
        local: PopularMoviesLocal
    ): PopularMoviesRepository = PopularMoviesRepositoryImpl(remote, local)

}