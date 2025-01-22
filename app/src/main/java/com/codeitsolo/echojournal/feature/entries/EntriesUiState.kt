package com.codeitsolo.echojournal.feature.entries

import androidx.compose.runtime.Immutable
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.core.models.AudioRecorderStatus

/**
 * UI state for the Entries screen
 *
 * @property isDataLoading Whether the screen is currently loading data
 * @property audioRecorderStatus The status of the audio recorder
 * @property currentlyRecordingAudioRecord The currently recording audio record
 */
@Immutable
internal data class EntriesUiState(
    val isDataLoading: Boolean = false,
    val audioRecorderStatus: AudioRecorderStatus = AudioRecorderStatus.Idle,
    val currentlyRecordingAudioRecord: AudioRecord? = null,
)
