package com.joe.movieDetails.data

import com.joe.data.json.NetworkProvider
import com.joe.movieDetails.repository.boundary.MovieDetailsRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataDi {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = NetworkProvider.createRetrofit()

    @Provides
    fun provideMovieDetailsRemote(retrofit: Retrofit): MovieDetailsRemote =
        MovieDetailsRemoteImpl(retrofit)

}