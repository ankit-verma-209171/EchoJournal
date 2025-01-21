package com.codeitsolo.echojournal.feature.entries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
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
        // TODO: Navigate to create record screen
    }

    private fun getDefaultUiState() = EntriesUiState()
}