package com.joe.cast

import com.joe.cast.presentation.converter.CastPresentationConverter
import com.joe.cast.presentation.converter.CastPresentationConverterImpl
import com.joe.cast.repository.converter.CastRepositoryConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CastDi {

    @Provides
    fun provideRepositoryConverter(): CastRepositoryConverter = CastRepositoryConverter()

    @Provides
    fun providePresentationConverter(): CastPresentationConverter = CastPresentationConverterImpl()
}