package com.joe.tvCast

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
import com.joe.tvCast.data.TvCastRemoteImpl
import com.joe.tvCast.local.TvCastDatabase
import com.joe.tvCast.local.dao.TvCastDao
import com.joe.tvCast.local.dao.TvCastDaoHelper
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
annotation class TvCast

@Module
@InstallIn(SingletonComponent::class)
object TvCastDi {

    @TvCast
    @Provides
    fun provideRemote(
        retrofit: Retrofit
    ): CastRemote = TvCastRemoteImpl(retrofit)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TvCastDatabase = Room.databaseBuilder(
        context,
        TvCastDatabase::class.java,
        "tv_cast_database"
    ).build()

    @Provides
    fun provideDao(database: TvCastDatabase): TvCastDao = database.tvCastDao()

    @TvCast
    @Provides
    fun provideDaoHelper(dao: TvCastDao): CastDaoHelper = TvCastDaoHelper(dao)

    @TvCast
    @Provides
    fun provideLocal(
        @TvCast daoHelper: CastDaoHelper
    ): CastLocal = CastLocalImpl(daoHelper)

    @Provides
    fun provideRepositoryConverter(): CastRepositoryConverter = CastRepositoryConverter()

    @TvCast
    @Provides
    fun provideRepository(
        @TvCast remote: CastRemote,
        @TvCast local: CastLocal,
        converter: CastRepositoryConverter,
    ): CastRepository = CastRepositoryImpl(remote, local, converter)

    @TvCast
    @Provides
    fun provideUseCase(
        @TvCast repository: CastRepository,
    ): CastUseCase = CastUseCase(repository)

    @Provides
    fun providePresentationConverter(): CastPresentationConverter = CastPresentationConverterImpl()

}