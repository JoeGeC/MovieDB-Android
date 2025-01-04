package com.joe.populartvshows

import android.content.Context
import androidx.room.Room
import com.joe.popular.data.PaginatedRemote
import com.joe.popular.domain.PaginatedUseCase
import com.joe.popular.local.PaginatedLocal
import com.joe.popular.repository.PaginatedRepository
import com.joe.popular.repository.PaginatedRepositoryImpl
import com.joe.populartvshows.data.PopularTvShowsRemoteImpl
import com.joe.populartvshows.local.PopularTvShowsDao
import com.joe.populartvshows.local.PopularTvShowsDatabase
import com.joe.populartvshows.local.PopularTvShowsLocalImpl
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
    ): PaginatedRemote<TvShowListItemResponse, PopularTvShowsResponse> =
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
        database: PopularTvShowsDatabase
    ): PaginatedLocal<PopularTvShowsLocalModel> =
        PopularTvShowsLocalImpl(database)

    @PopularTvShows
    @Provides
    fun providePopularTvShowsRepository(
        @PopularTvShows remote: PaginatedRemote<TvShowListItemResponse, PopularTvShowsResponse>,
        @PopularTvShows local: PaginatedLocal<PopularTvShowsLocalModel>,
        @PopularTvShows converter: PopularTvShowsRepositoryConverter
    ): PaginatedRepository = PaginatedRepositoryImpl(remote, local, converter)

    @PopularTvShows
    @Provides
    fun providePopularTvShowsRepositoryConverter(): PopularTvShowsRepositoryConverter =
        PopularTvShowsRepositoryConverter()

    @PopularTvShows
    @Provides
    fun providePopularTvShowsUseCase(
        @PopularTvShows repository: PaginatedRepository
    ): PaginatedUseCase = PaginatedUseCase(repository)
}
