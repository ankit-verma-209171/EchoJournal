package com.codeitsolo.echojournal.di

import android.app.Application
import android.content.Context
import com.codeitsolo.echojournal.navigation.Navigator
import com.codeitsolo.echojournal.navigation.NavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module that provides app dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides application's [Context].
     */
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    /**
     * Provides [NavigatorImpl] to [Navigator].
     */
    @Provides
    @Singleton
    fun provideNavigator(): Navigator = NavigatorImpl()
}