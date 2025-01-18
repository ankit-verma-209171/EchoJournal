package com.codeitsolo.echojournal.di

import com.codeitsolo.echojournal.navigation.Navigator
import com.codeitsolo.echojournal.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module that provides app dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    /**
     * Binds [NavigatorImpl] to [Navigator].
     */
    @Binds
    fun bindNavigator(navigatorImpl: NavigatorImpl): Navigator
}