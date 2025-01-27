package com.codeitsolo.echojournal.feature.entries.create

import androidx.compose.runtime.Immutable
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.core.models.AudioRecord

/**
 * UI state for the Create Entry screen.
 *
 * @property title The title of the entry.
 * @property topics The topics associated with the entry.
 * @property audioRecord The audio record associated with the entry.
 * @property description The description of the entry.
 * @property mood The mood associated with the entry.
 * @property selectedMood The selected mood.
 * @property isSavingEntry Whether the entry is being saved.
 * @property isSelectingMood Whether the mood is being selected.
 */
@Immutable
internal data class CreateEntryUiState(
    val title: String = "",
    val topics: List<String> = emptyList(),
    val audioRecord: AudioRecord,
    val description: String = "",
    val mood: Mood? = null,
    val selectedMood: Mood? = null,
    val isSavingEntry: Boolean = false,
    val isSelectingMood: Boolean = false,
) {
    val validDetails: Boolean = title.isNotBlank() && description.isNotBlank()
}

/**
 * Enum class representing the moods.
 */
@Immutable
internal enum class Mood(
    val selectedIcon: Int,
    val unselectedIcon: Int
) {
    Stressed(R.drawable.stressed, R.drawable.stressed_outlined),
    Sad(R.drawable.sad, R.drawable.sad_outlined),
    Neutral(R.drawable.neutral, R.drawable.neutral_outlined),
    Peaceful(R.drawable.peaceful, R.drawable.peaceful_outlined),
    Excited(R.drawable.excited, R.drawable.excited_outlined),
}
