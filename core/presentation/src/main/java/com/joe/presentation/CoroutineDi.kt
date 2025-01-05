package com.joe.presentation

import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@dagger.Module
@dagger.hilt.InstallIn(SingletonComponent::class)
object CoroutineDi {

    @IoDispatcher
    @dagger.Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}

@javax.inject.Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher
