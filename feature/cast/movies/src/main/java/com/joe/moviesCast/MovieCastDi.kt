package com.joe.moviesCast

import android.content.Context
import androidx.room.Room
import com.joe.cast.data.CastRemote
import com.joe.cast.domain.CastUseCase
import com.joe.cast.local.CastDaoHelper
import com.joe.cast.local.CastLocal
import com.joe.cast.local.CastLocalImpl
import com.joe.cast.presentation.converter.CastPresentationConverter
import com.joe.cast.presentation.converter.CastPresentationConverterImpl
import com.joe.cast.repository.CastRepository
import com.joe.cast.repository.converter.CastRepositoryConverter
import com.joe.cast.repository.CastRepositoryImpl
import com.joe.moviesCast.data.MovieCastRemoteImpl
import com.joe.moviesCast.local.MovieCastDatabase
import com.joe.moviesCast.local.dao.MovieCastDao
import com.joe.moviesCast.local.dao.MovieCastDaoHelper
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
annotation class MovieCast

@Module
@InstallIn(SingletonComponent::class)
object TvCastDi {

    @MovieCast
    @Provides
    fun provideRemote(
        retrofit: Retrofit
    ): CastRemote = MovieCastRemoteImpl(retrofit)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieCastDatabase = Room.databaseBuilder(
        context,
        MovieCastDatabase::class.java,
        "movie_cast_database"
    ).build()

    @Provides
    fun provideDao(database: MovieCastDatabase): MovieCastDao = database.movieCastDao()

    @MovieCast
    @Provides
    fun provideDaoHelper(dao: MovieCastDao): CastDaoHelper = MovieCastDaoHelper(dao)

    @MovieCast
    @Provides
    fun provideLocal(
        @MovieCast daoHelper: CastDaoHelper
    ): CastLocal = CastLocalImpl(daoHelper)

    @MovieCast
    @Provides
    fun provideRepository(
        @MovieCast remote: CastRemote,
        @MovieCast local: CastLocal,
        converter: CastRepositoryConverter,
    ): CastRepository = CastRepositoryImpl(remote, local, converter)

    @MovieCast
    @Provides
    fun provideUseCase(
        @MovieCast repository: CastRepository,
    ): CastUseCase = CastUseCase(repository)

}