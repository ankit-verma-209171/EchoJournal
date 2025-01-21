package com.codeitsolo.echojournal.feature.entries

import androidx.compose.runtime.Immutable

/**
 * UI state for the Entries screen
 *
 * @property isDataLoading Whether the screen is currently loading data
 */
@Immutable
internal data class EntriesUiState(
    val isDataLoading: Boolean = false,
)
