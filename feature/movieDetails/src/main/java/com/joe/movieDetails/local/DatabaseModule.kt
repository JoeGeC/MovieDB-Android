package com.joe.movieDetails.local

import android.content.Context
import androidx.room.Room

object DatabaseModule {

    fun provideDatabase(context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "movie_database"
    ).build()

    fun provideMovieDetailsDao(database: MovieDatabase): MovieDetailsDao =
        database.movieDetailsDao()
}
