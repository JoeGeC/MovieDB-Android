package com.joe.populartvshows

import android.content.Context
import androidx.room.Room
import com.joe.popular.data.PopularRemote
import com.joe.popular.domain.PopularUseCase
import com.joe.popular.local.PopularLocal
import com.joe.popular.local.PopularLocalImpl
import com.joe.popular.repository.PopularRepository
import com.joe.popular.repository.PopularRepositoryImpl
import com.joe.populartvshows.data.PopularTvShowsRemoteImpl
import com.joe.populartvshows.local.dao.PopularTvShowsDao
import com.joe.populartvshows.local.dao.PopularTvShowsDaoHelper
import com.joe.populartvshows.local.database.PopularTvShowsDatabase
import com.joe.populartvshows.local.model.PopularTvShowsLocalModel
import com.joe.populartvshows.repository.converter.PopularTvShowsRepositoryConverter
import com.joe.populartvshows.repository.response.PopularTvShowsResponse
import com.joe.populartvshows.repository.response.TvShowListItemResponse
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
annotation class PopularTvShows

@Module
@InstallIn(SingletonComponent::class)
object PopularTvShowsDi {

    @PopularTvShows
    @Provides
    fun providePopularTvShowsRemote(
        retrofit: Retrofit
    ): PopularRemote<TvShowListItemResponse, PopularTvShowsResponse> =
        PopularTvShowsRemoteImpl(retrofit)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PopularTvShowsDatabase = Room.databaseBuilder(
        context,
        PopularTvShowsDatabase::class.java,
        "popular_tv_shows_database"
    ).build()

    @Provides
    fun providePopularTvShowsDao(database: PopularTvShowsDatabase): PopularTvShowsDao =
        database.popularTvShowsDao()

    @PopularTvShows
    @Provides
    fun providePopularTvShowsLocal(
        daoHelper: PopularTvShowsDaoHelper
    ): PopularLocal<PopularTvShowsLocalModel> =
        PopularLocalImpl<PopularTvShowsLocalModel>(daoHelper)

    @PopularTvShows
    @Provides
    fun providePopularTvShowsRepository(
        @PopularTvShows remote: PopularRemote<TvShowListItemResponse, PopularTvShowsResponse>,
        @PopularTvShows local: PopularLocal<PopularTvShowsLocalModel>,
        @PopularTvShows converter: PopularTvShowsRepositoryConverter
    ): PopularRepository = PopularRepositoryImpl(remote, local, converter)

    @PopularTvShows
    @Provides
    fun providePopularTvShowsRepositoryConverter(): PopularTvShowsRepositoryConverter =
        PopularTvShowsRepositoryConverter()

    @PopularTvShows
    @Provides
    fun providePopularTvShowsUseCase(
        @PopularTvShows repository: PopularRepository
    ): PopularUseCase = PopularUseCase(repository)
}
