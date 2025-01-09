package com.joe.popularmovies

import android.content.Context
import androidx.room.Room
import com.joe.popular.data.PopularRemote
import com.joe.popular.domain.PopularUseCase
import com.joe.popular.local.PopularLocal
import com.joe.popular.local.PopularLocalImpl
import com.joe.popular.repository.PopularRepository
import com.joe.popular.repository.PopularRepositoryImpl
import com.joe.popularmovies.data.PopularMoviesRemoteImpl
import com.joe.popularmovies.local.dao.PopularMoviesDao
import com.joe.popularmovies.local.dao.PopularMoviesDaoHelper
import com.joe.popularmovies.local.database.PopularMoviesDatabase
import com.joe.popularmovies.local.model.PopularMoviesLocalModel
import com.joe.popularmovies.repository.converter.PopularMoviesRepositoryConverter
import com.joe.popularmovies.data.response.MovieListItemResponse
import com.joe.popularmovies.data.response.PopularMoviesResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PopularMovies

@Module
@InstallIn(SingletonComponent::class)
object PopularMoviesDi {

    @PopularMovies
    @Provides
    fun provideRemote(
        retrofit: Retrofit
    ): PopularRemote<MovieListItemResponse, PopularMoviesResponse> =
        PopularMoviesRemoteImpl(retrofit)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PopularMoviesDatabase = Room.databaseBuilder(
        context,
        PopularMoviesDatabase::class.java,
        "popular_movies_database"
    ).build()

    @Provides
    fun provideDao(database: PopularMoviesDatabase): PopularMoviesDao =
        database.popularMoviesDao()

    @PopularMovies
    @Provides
    fun provideLocal(
        daoHelper: PopularMoviesDaoHelper
    ): PopularLocal<PopularMoviesLocalModel> =
        PopularLocalImpl<PopularMoviesLocalModel>(daoHelper)

    @PopularMovies
    @Provides
    fun provideRepository(
        @PopularMovies remote: PopularRemote<MovieListItemResponse, PopularMoviesResponse>,
        @PopularMovies local: PopularLocal<PopularMoviesLocalModel>,
        @PopularMovies converter: PopularMoviesRepositoryConverter
    ): PopularRepository = PopularRepositoryImpl(remote, local, converter)

    @PopularMovies
    @Provides
    fun provideRepositoryConverter(): PopularMoviesRepositoryConverter =
        PopularMoviesRepositoryConverter()

    @PopularMovies
    @Provides
    fun provideUseCase(
        @PopularMovies repository: PopularRepository
    ): PopularUseCase = PopularUseCase(repository)
}
