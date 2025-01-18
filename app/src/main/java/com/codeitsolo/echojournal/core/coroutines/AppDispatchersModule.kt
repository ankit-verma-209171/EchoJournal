package com.codeitsolo.echojournal.core.coroutines

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Module that provides dispatchers for coroutines.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppDispatchersModule {

    /**
     * Provides the main dispatcher for coroutines.
     */
    @Provides
    @Dispatcher(AppDispatchers.MAIN)
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    /**
     * Provides the IO dispatcher for coroutines.
     */
    @Provides
    @Dispatcher(AppDispatchers.IO)
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    /**
     * Provides the default dispatcher for coroutines.
     */
    @Provides
    @Dispatcher(AppDispatchers.DEFAULT)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}