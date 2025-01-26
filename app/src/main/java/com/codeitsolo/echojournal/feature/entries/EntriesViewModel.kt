package com.codeitsolo.echojournal.feature.entries

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeitsolo.echojournal.core.domain.recorder.AudioRecorder
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.core.models.AudioRecorderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.io.File
import javax.inject.Inject

/**
 * View model for the [EntriesRoute]
 */
@HiltViewModel
internal class EntriesViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val audioRecorder: AudioRecorder,
) : ViewModel() {

    private val _uiState = MutableStateFlow(getDefaultUiState())
    val uiState = _uiState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = getDefaultUiState()
        )

    var audioFile: File? = null

    fun createRecord() {
        val newAudioRecord = AudioRecord()
        File(context.cacheDir, newAudioRecord.fileName).also {
            audioRecorder.start(it)
            audioFile = it
        }
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Recording,
                currentlyRecordingAudioRecord = newAudioRecord
            )
        }
    }

    fun onPauseRecordingClick() {
        audioRecorder.pause()
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Paused,
            )
        }
    }

    fun onResumeRecordingClick() {
        audioRecorder.resume()
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Recording,
            )
        }
    }

    fun onCompleteRecordingClick() {
        audioRecorder.stop()
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Idle,
                currentlyRecordingAudioRecord = null,
            )
        }
    }

    fun onCancelRecordingClick() {
        audioRecorder.stop()
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Idle,
                currentlyRecordingAudioRecord = null,
            )
        }
    }

    private fun getDefaultUiState() = EntriesUiState()
}