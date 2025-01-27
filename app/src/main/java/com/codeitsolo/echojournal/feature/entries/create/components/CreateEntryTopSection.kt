package com.codeitsolo.echojournal.feature.entries.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.feature.entries.create.CreateEntryUiState
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme

/**
 * Create Entry Top Section.
 *
 * @param modifier The Modifier to be applied to this composable.
 * @param uiState [CreateEntryUiState]
 * @param onTitleChange Callback for title change.
 * @param onChangeMoodClick Callback for change mood click.
 */
@Composable
internal fun CreateEntryTopSection(
    modifier: Modifier = Modifier,
    uiState: CreateEntryUiState,
    onTitleChange: (String) -> Unit,
    onChangeMoodClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onChangeMoodClick
                ),
            painter = painterResource(
                if (uiState.mood != null) {
                    uiState.mood.selectedIcon
                } else {
                    R.drawable.add_mood
                }
            ),
            contentDescription = stringResource(R.string.add_mood),
            tint = Color.Unspecified
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = uiState.title,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.add_title),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            textStyle = MaterialTheme.typography.headlineLarge,
            singleLine = true,
            onValueChange = onTitleChange
        )
    }
}

// region Preview

@Preview
@Composable
private fun CreateEntryTopSectionPreview() {
    EchoJournalTheme {
        CreateEntryTopSection(
            modifier = Modifier.background(Color.White),
            onChangeMoodClick = { },
            uiState = CreateEntryUiState(
                title = "", audioRecord = AudioRecord()
            ),
            onTitleChange = { }
        )
    }
}

// endregion