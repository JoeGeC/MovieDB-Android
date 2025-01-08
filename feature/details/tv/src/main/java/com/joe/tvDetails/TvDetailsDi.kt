package com.joe.tvDetails

import android.content.Context
import androidx.room.Room
import com.joe.base.data.DetailsRemote
import com.joe.base.local.DetailsLocal
import com.joe.base.local.DetailsLocalImpl
import com.joe.base.presentation.converter.DetailsPresentationConverter
import com.joe.base.repository.DetailsRepository
import com.joe.base.repository.DetailsRepositoryImpl
import com.joe.base.usecase.DetailsUseCase
import com.joe.tvDetails.data.TvDetailsRemoteImpl
import com.joe.tvDetails.domain.TvDetailsEntity
import com.joe.tvDetails.local.TvDatabase
import com.joe.tvDetails.local.dao.TvDetailsDao
import com.joe.tvDetails.local.dao.TvDetailsDaoHelper
import com.joe.tvDetails.local.model.TvDetailsLocalModel
import com.joe.tvDetails.presentation.converter.TvDetailsPresentationConverter
import com.joe.tvDetails.presentation.model.TvDetailsModel
import com.joe.tvDetails.repository.converter.TvDetailsRepositoryConverter
import com.joe.tvDetails.repository.response.TvDetailsResponse
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
annotation class TvDetails

@Module
@InstallIn(SingletonComponent::class)
object TvDetailsDi {

    @TvDetails
    @Provides
    fun provideRemote(
        retrofit: Retrofit
    ): DetailsRemote<TvDetailsResponse> =
        TvDetailsRemoteImpl(retrofit)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TvDatabase = Room.databaseBuilder(
        context,
        TvDatabase::class.java,
        "tv_database"
    ).build()

    @Provides
    fun provideDao(database: TvDatabase): TvDetailsDao =
        database.tvDetailsDao()

    @TvDetails
    @Provides
    fun provideLocal(
        daoHelper: TvDetailsDaoHelper
    ): DetailsLocal<TvDetailsLocalModel> =
        DetailsLocalImpl<TvDetailsLocalModel>(daoHelper)

    @TvDetails
    @Provides
    fun provideRepository(
        @TvDetails remote: DetailsRemote<TvDetailsResponse>,
        @TvDetails local: DetailsLocal<TvDetailsLocalModel>,
        @TvDetails converter: TvDetailsRepositoryConverter
    ): DetailsRepository<TvDetailsEntity> = DetailsRepositoryImpl(remote, local, converter)

    @TvDetails
    @Provides
    fun provideRepositoryConverter(): TvDetailsRepositoryConverter =
        TvDetailsRepositoryConverter()

    @TvDetails
    @Provides
    fun provideUseCase(
        @TvDetails repository: DetailsRepository<TvDetailsEntity>
    ): DetailsUseCase<TvDetailsEntity> = DetailsUseCase(repository)

    @TvDetails
    @Provides
    fun providePresentationConverter():
            DetailsPresentationConverter<TvDetailsModel, TvDetailsEntity> =
        TvDetailsPresentationConverter()

}