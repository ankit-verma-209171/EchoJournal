package com.codeitsolo.echojournal.core.domain.timer

import kotlinx.coroutines.flow.Flow

/**
 * Interface for a timer.
 */
interface Timer {

    /**
     * The current time in milliseconds.
     */
    val time: Flow<Long>

    /**
     * Starts the timer.
     */
    fun start()

    /**
     * Resumes the timer.
     */
    fun resume()

    /**
     * Stops the timer.
     */
    fun stop()

    /**
     * Cancels the timer.
     */
    fun cancel()
}