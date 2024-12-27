package com.joe.movieDetails.local

import android.content.Context
import androidx.room.Room
import com.joe.movieDetails.repository.boundary.MovieDetailsLocal
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
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "movie_database"
    ).build()

    @Provides
    fun provideMovieDetailsDao(database: MovieDatabase): MovieDetailsDao =
        database.movieDetailsDao()

    @Provides
    fun provideMovieDetailsLocal(database: MovieDatabase): MovieDetailsLocal =
        MovieDetailsLocalImpl(database)
}