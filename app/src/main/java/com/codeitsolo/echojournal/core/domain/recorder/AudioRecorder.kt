package com.codeitsolo.echojournal.core.domain.recorder

import java.io.File

/**
 * Interface for recording audio.
 */
interface AudioRecorder {

    /**
     * Starts recording audio to the given output file.
     *
     * @param outputFile The file to record audio to.
     */
    fun start(outputFile: File)

    /**
     * Pauses the currently recording audio.
     */
    fun pause()

    /**
     * Resumes the paused recorded audio.
     */
    fun resume()

    /**
     * Stops and finishes recording audio.
     */
    fun stop()
}