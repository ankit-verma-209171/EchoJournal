package com.codeitsolo.echojournal.core.models

import androidx.compose.runtime.Immutable

/**
 * Status of the audio recorder.
 */
@Immutable
enum class AudioRecorderStatus {
    /**
     * The audio recorder is idle.
     */
    Idle,

    /**
     * The audio recorder is recording.
     */
    Recording,

    /**
     * The audio recorder is paused.
     */
    Paused,
}