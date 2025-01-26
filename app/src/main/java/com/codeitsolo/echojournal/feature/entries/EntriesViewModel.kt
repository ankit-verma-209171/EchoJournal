package com.codeitsolo.echojournal.feature.entries

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeitsolo.echojournal.core.coroutines.AppDispatchers
import com.codeitsolo.echojournal.core.coroutines.Dispatcher
import com.codeitsolo.echojournal.core.domain.recorder.AudioRecorder
import com.codeitsolo.echojournal.core.domain.timer.Timer
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.core.models.AudioRecorderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

/**
 * View model for the [EntriesRoute]
 */
@HiltViewModel
internal class EntriesViewModel @Inject constructor(
    @Dispatcher(AppDispatchers.DEFAULT) private val defaultDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context,
    private val audioRecorder: AudioRecorder,
    private val timer: Timer,
) : ViewModel() {

    private val _uiState = MutableStateFlow(getDefaultUiState())
    val uiState = _uiState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = getDefaultUiState()
        )

    private var audioFile: File? = null
    private var timerJob: Job? = null

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
        createAndStartTimer { durationSeconds ->
            _uiState.update {
                it.copy(
                    currentlyRecordingAudioRecord = it
                        .currentlyRecordingAudioRecord
                        ?.copy(durationSeconds = durationSeconds)
                )
            }
        }
    }

    fun onPauseRecordingClick() {
        audioRecorder.pause()
        timer.stop()
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Paused,
            )
        }
    }

    fun onResumeRecordingClick() {
        audioRecorder.resume()
        timer.resume()
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Recording,
            )
        }
    }

    fun onCompleteRecordingClick() {
        audioRecorder.stop()
        cancelTimer()
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Idle,
                currentlyRecordingAudioRecord = null,
            )
        }
    }

    fun onCancelRecordingClick() {
        audioRecorder.stop()
        cancelTimer()
        _uiState.update {
            it.copy(
                audioRecorderStatus = AudioRecorderStatus.Idle,
                currentlyRecordingAudioRecord = null,
            )
        }
    }

    private fun createAndStartTimer(onTimerTick: (Long) -> Unit) {
        timerJob?.cancel()
        timerJob = viewModelScope.launch(defaultDispatcher) {
            launch {
                timer.time.collect { onTimerTick(it) }
            }
            timer.start()
        }
        timerJob?.invokeOnCompletion {
            timerJob = null
        }
    }

    private fun cancelTimer() {
        timerJob?.cancel()
        timerJob = null
        timer.stop()
        timer.cancel()
    }

    private fun getDefaultUiState() = EntriesUiState()
}