package com.codeitsolo.echojournal.feature.entries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.core.models.AudioRecorderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * View model for the [EntriesRoute]
 */
@HiltViewModel
internal class EntriesViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(getDefaultUiState())
    val uiState = _uiState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = getDefaultUiState()
        )

    fun onCreateRecordClick() {
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Recording,
                currentlyRecordingAudioRecord = AudioRecord()
            )
        }
    }

    fun onStopRecordingClick() {
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Paused,
            )
        }
    }

    fun onStartRecordingClick() {
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Recording,
            )
        }
    }

    fun onCompleteRecordingClick() {
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Idle,
                currentlyRecordingAudioRecord = null,
            )
        }
    }

    fun onCancelRecordingClick() {
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Idle,
                currentlyRecordingAudioRecord = null,
            )
        }
    }

    private fun getDefaultUiState() = EntriesUiState()
}