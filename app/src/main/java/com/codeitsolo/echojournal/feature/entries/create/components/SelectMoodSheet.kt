package com.codeitsolo.echojournal.feature.entries.create.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.feature.entries.create.CreateEntryUiState
import com.codeitsolo.echojournal.feature.entries.create.Mood
import com.codeitsolo.echojournal.ui.components.buttons.PrimaryButton
import com.codeitsolo.echojournal.ui.components.buttons.SecondaryButton
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme
import com.codeitsolo.echojournal.ui.theme.color.secondary70

/**
 * Select mood sheet to allow user to select mood.
 *
 * @param modifier The Modifier to be applied to this composable.
 * @param uiState [CreateEntryUiState]
 * @param onMoodClick Callback for mood click.
 * @param onCancelMoodClick Callback for cancel mood click.
 * @param onSaveMoodClick Callback for save mood click.
 */
@Composable
internal fun SelectMoodSheet(
    modifier: Modifier = Modifier,
    onMoodClick: (Mood) -> Unit,
    uiState: CreateEntryUiState,
    onCancelMoodClick: () -> Unit,
    onSaveMoodClick: (Mood) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.how_are_you_doing),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp, bottom = 24.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Mood.entries.fastForEach { mood ->
                Column {
                    Icon(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { onMoodClick(mood) }
                            )
                            .padding(bottom = 8.dp),
                        painter = painterResource(
                            if (mood == uiState.selectedMood) {
                                mood.selectedIcon
                            } else {
                                mood.unselectedIcon
                            },
                        ),
                        tint = if (mood == uiState.selectedMood) {
                            Color.Unspecified
                        } else {
                            secondary70
                        },
                        contentDescription = mood.name,
                    )
                    Text(
                        text = mood.name,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            SecondaryButton(
                modifier = Modifier
                    .weight(2f),
                text = stringResource(R.string.cancel),
                onClick = onCancelMoodClick
            )

            PrimaryButton(
                modifier = Modifier
                    .weight(5f),
                text = stringResource(R.string.confirm),
                icon = painterResource(R.drawable.ic_check),
                enabled = uiState.selectedMood != null,
                onClick = { uiState.selectedMood?.let { onSaveMoodClick(it) } }
            )
        }
    }
}

// region Preview

@Preview
@Composable
fun SelectMoodSheetPreview() {
    EchoJournalTheme {
        val uiState = CreateEntryUiState(
            audioRecord = AudioRecord(),
            selectedMood = Mood.Neutral
        )
        Surface {
            SelectMoodSheet(
                modifier = Modifier
                    .padding(8.dp),
                onMoodClick = {
                    Log.d("SelectMoodSheetPreview", "Mood clicked: ${it.name}")
                },
                uiState = uiState,
                onCancelMoodClick = {},
                onSaveMoodClick = {}
            )
        }
    }
}

// endregion