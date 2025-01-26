package com.codeitsolo.echojournal.core.domain.di

import com.codeitsolo.echojournal.core.domain.playback.AndroidAudioPlayer
import com.codeitsolo.echojournal.core.domain.playback.AudioPlayer
import com.codeitsolo.echojournal.core.domain.recorder.AndroidAudioRecorder
import com.codeitsolo.echojournal.core.domain.recorder.AudioRecorder
import com.codeitsolo.echojournal.core.domain.timer.Timer
import com.codeitsolo.echojournal.core.domain.timer.TimerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module that provides domain dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    /**
     * Binds [AndroidAudioRecorder] to [AudioRecorder].
     */
    @Binds
    fun bindAudioRecorder(impl: AndroidAudioRecorder): AudioRecorder

    /**
     * Binds [AndroidAudioPlayer] to [AudioPlayer].
     */
    @Binds
    fun bindAudioPlayer(impl: AndroidAudioPlayer): AudioPlayer

    /**
     * Binds [TimerImpl] to [Timer].
     */
    @Binds
    fun bindTimer(impl: TimerImpl): Timer
}