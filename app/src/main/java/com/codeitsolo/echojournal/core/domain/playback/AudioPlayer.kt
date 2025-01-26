package com.codeitsolo.echojournal.core.domain.playback

import java.io.File

/**
 * Interface for recording audio.
 */
interface AudioPlayer {

    /**
     * Plays the given audio file.
     *
     * @param file The audio file to play.
     */
    fun playFile(file: File)

    /**
     * Stops the playback.
     */
    fun stop()
}