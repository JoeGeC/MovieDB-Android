package com.joe.movieDetails

import android.content.Context
import androidx.room.Room
import com.joe.base.data.DetailsRemote
import com.joe.base.local.DetailsLocal
import com.joe.base.local.DetailsLocalImpl
import com.joe.base.presentation.converter.DetailsPresentationConverter
import com.joe.base.repository.DetailsRepository
import com.joe.base.repository.DetailsRepositoryImpl
import com.joe.base.usecase.DetailsUseCase
import com.joe.movieDetails.data.MovieDetailsRemoteImpl
import com.joe.movieDetails.domain.MovieDetailsEntity
import com.joe.movieDetails.local.MovieDatabase
import com.joe.movieDetails.local.dao.MovieDetailsDao
import com.joe.movieDetails.local.dao.MovieDetailsDaoHelper
import com.joe.movieDetails.local.model.MovieDetailsLocalModel
import com.joe.movieDetails.presentation.model.MovieDetailsModel
import com.joe.movieDetails.presentation.converter.MovieDetailsPresentationConverter
import com.joe.movieDetails.repository.converter.MovieDetailsRepositoryConverter
import com.joe.movieDetails.repository.response.MovieDetailsResponse
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
annotation class MovieDetails

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsDi {

    @MovieDetails
    @Provides
    fun provideRemote(
        retrofit: Retrofit
    ): DetailsRemote<MovieDetailsResponse> =
        MovieDetailsRemoteImpl(retrofit)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "movie_database"
    ).build()

    @Provides
    fun provideDao(database: MovieDatabase): MovieDetailsDao =
        database.movieDetailsDao()

    @MovieDetails
    @Provides
    fun provideLocal(
        daoHelper: MovieDetailsDaoHelper
    ): DetailsLocal<MovieDetailsLocalModel> =
        DetailsLocalImpl<MovieDetailsLocalModel>(daoHelper)

    @MovieDetails
    @Provides
    fun provideRepository(
        @MovieDetails remote: DetailsRemote<MovieDetailsResponse>,
        @MovieDetails local: DetailsLocal<MovieDetailsLocalModel>,
        @MovieDetails converter: MovieDetailsRepositoryConverter
    ): DetailsRepository<MovieDetailsEntity> = DetailsRepositoryImpl(remote, local, converter)

    @MovieDetails
    @Provides
    fun provideRepositoryConverter(): MovieDetailsRepositoryConverter =
        MovieDetailsRepositoryConverter()

    @MovieDetails
    @Provides
    fun provideUseCase(
        @MovieDetails repository: DetailsRepository<MovieDetailsEntity>
    ): DetailsUseCase<MovieDetailsEntity> = DetailsUseCase(repository)

    @MovieDetails
    @Provides
    fun providePresentationConverter():
            DetailsPresentationConverter<MovieDetailsModel, MovieDetailsEntity> =
        MovieDetailsPresentationConverter()

}