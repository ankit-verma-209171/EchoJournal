package com.codeitsolo.echojournal.core.extensions

import com.codeitsolo.echojournal.core.models.AudioRecorderStatus

/**
 * The representable message for the audio recorder status.
 */
val AudioRecorderStatus.message: String
    get() = when (this) {
        AudioRecorderStatus.Idle -> ""
        AudioRecorderStatus.Recording -> "Recording your memories..."
        AudioRecorderStatus.Paused -> "Recording paused"
    }
