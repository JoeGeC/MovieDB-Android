package com.joe.popularmovies.data

import com.joe.popularmovies.repository.boundary.PopularMoviesRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataDi {

    @Provides
    fun provideMovieDetailsRemote(retrofit: Retrofit): PopularMoviesRemote =
        PopularMoviesRemoteImpl(retrofit)

}