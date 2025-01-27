package com.codeitsolo.echojournal.feature.entries.create.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.feature.entries.create.CreateEntryUiState
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme

/**
 * A standalone screen to allow user to create entry.
 *
 * @param modifier The Modifier to be applied to this composable.
 * @param uiState [CreateEntryUiState]
 * @param onDescriptionChange Callback for description change.
 */
@Composable
internal fun CreateEntryMiddleSection(
    modifier: Modifier = Modifier,
    uiState: CreateEntryUiState,
    onDescriptionChange: (String) -> Unit
) {
    Row(
        modifier = modifier,
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 6.dp),
            painter = painterResource(R.drawable.ic_edit),
            contentDescription = stringResource(R.string.edit_description),
            tint = MaterialTheme.colorScheme.outlineVariant
        )

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = uiState.description,
            textStyle = MaterialTheme.typography.bodyMedium,
            decorationBox = { innerTextField ->
                if (uiState.description.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(R.string.add_description),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        textAlign = Start
                    )
                }
                innerTextField()
            },
            onValueChange = onDescriptionChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
        )
    }
}

// region Preview

@Preview
@Composable
private fun CreateEntryMiddleSectionPreview() {
    EchoJournalTheme {
        Surface {
            CreateEntryMiddleSection(
                modifier = Modifier
                    .padding(8.dp),
                uiState = CreateEntryUiState(
                    description = "",
                    audioRecord = AudioRecord()
                ),
                onDescriptionChange = { }
            )
        }
    }
}

// endregion