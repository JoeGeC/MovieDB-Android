package com.joe.popularmovies.local

import android.content.Context
import androidx.room.Room
import com.joe.popularmovies.repository.boundary.PopularMoviesLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDi {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PopularMoviesDatabase = Room.databaseBuilder(
        context,
        PopularMoviesDatabase::class.java,
        "popular_movies_database"
    ).build()

    @Provides
    fun providePopularMoviesDao(database: PopularMoviesDatabase): PopularMoviesDao =
        database.popularMoviesDao()

    @Provides
    fun providePopularMoviesLocal(database: PopularMoviesDatabase): PopularMoviesLocal =
        PopularMoviesLocalImpl(database)
}