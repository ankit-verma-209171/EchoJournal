package com.codeitsolo.echojournal.feature.entries.create

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.codeitsolo.echojournal.feature.entries.navigation.CreateEntry
import com.codeitsolo.echojournal.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * ViewModel for the Create Entry screen.
 */
@HiltViewModel
internal class CreateEntryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
) : ViewModel() {

    private val navArgs = savedStateHandle.toRoute<CreateEntry>(
        typeMap = CreateEntry.toTypeMap()
    )

    private val _uiState = MutableStateFlow(getDefaultUiState())
    val uiState: StateFlow<CreateEntryUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = getDefaultUiState()
    )

    fun onBackClick() {
        navigator.navigateUp()
    }

    fun onTitleChange(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun onDescriptionChange(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun onSaveEntryClick() {

        // TODO: Save and navigate up!

        navigator.navigateUp()
    }

    fun onCancelClick() {
        navigator.navigateUp()
    }

    fun onChangeMoodClick() {
        _uiState.update { it.copy(isSelectingMood = true, selectedMood = it.mood) }
    }

    fun onMoodClick(mood: Mood) {
        _uiState.update { it.copy(selectedMood = mood) }
    }

    fun cancelMoodClick() {
        _uiState.update { it.copy(isSelectingMood = false) }
    }

    fun onSaveMoodClick(mood: Mood) {
        _uiState.update { it.copy(isSelectingMood = false, mood = mood) }
    }

    private fun getDefaultUiState() = CreateEntryUiState(
        audioRecord = navArgs.audioRecord
    )
}