package com.codeitsolo.echojournal.feature.entries.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.feature.entries.create.components.CreateEntryMiddleSection
import com.codeitsolo.echojournal.feature.entries.create.components.CreateEntryTopBar
import com.codeitsolo.echojournal.feature.entries.create.components.CreateEntryTopSection
import com.codeitsolo.echojournal.feature.entries.create.components.SelectMoodSheet
import com.codeitsolo.echojournal.ui.components.buttons.PrimaryButton
import com.codeitsolo.echojournal.ui.components.buttons.SecondaryButton
import com.codeitsolo.echojournal.ui.components.scaffold.GradientScaffoldX
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme

/**
 * Create Entry route to show the Create Entry screen.
 *
 * @param modifier The Modifier to be applied to this composable.
 * @param viewModel [CreateEntryViewModel]
 */
@Composable
internal fun CreateEntryRoute(
    modifier: Modifier = Modifier,
    viewModel: CreateEntryViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CreateEntryScreen(
        modifier = modifier,
        uiState = uiState,
        onBackClick = viewModel::onBackClick,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onSaveEntryClick = viewModel::onSaveEntryClick,
        onCancelClick = viewModel::onCancelClick,
        onChangeMoodClick = viewModel::onChangeMoodClick,
        onMoodClick = viewModel::onMoodClick,
        onSaveMoodClick = viewModel::onSaveMoodClick,
        onCancelMoodClick = viewModel::cancelMoodClick
    )
}

/**
 * A standalone screen to allow user to create entry.
 *
 * @param modifier The Modifier to be applied to this composable.
 * @param uiState [CreateEntryUiState]
 * @param onBackClick Callback for back click.
 * @param onTitleChange Callback for title change.
 * @param onDescriptionChange Callback for description change.
 * @param onSaveEntryClick Callback for save entry click.
 * @param onCancelClick Callback for cancel click.
 * @param onChangeMoodClick Callback for change mood click.
 * @param onMoodClick Callback for mood click.
 * @param onSaveMoodClick Callback for save mood click.
 * @param onCancelMoodClick Callback for cancel mood click.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateEntryScreen(
    modifier: Modifier = Modifier,
    uiState: CreateEntryUiState,
    onBackClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveEntryClick: () -> Unit,
    onCancelClick: () -> Unit,
    onChangeMoodClick: () -> Unit,
    onMoodClick: (Mood) -> Unit,
    onSaveMoodClick: (Mood) -> Unit,
    onCancelMoodClick: () -> Unit,
) {
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Hidden,
        skipHiddenState = false
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState,
    )

    LaunchedEffect(uiState.isSelectingMood) {
        if (uiState.isSelectingMood) {
            bottomSheetState.expand()
        } else {
            bottomSheetState.hide()
        }
    }

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetSwipeEnabled = false,
        sheetContainerColor = MaterialTheme.colorScheme.background,
        sheetDragHandle = {
            Surface(
                modifier = modifier.padding(vertical = 16.dp),
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(100.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(
                            width = 32.dp,
                            height = 4.dp
                        )
                )
            }
        },
        sheetContent = {
            SelectMoodSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(top = 18.dp, bottom = 8.dp),
                onMoodClick = onMoodClick,
                uiState = uiState,
                onCancelMoodClick = onCancelMoodClick,
                onSaveMoodClick = onSaveMoodClick
            )
        }
    ) {
        GradientScaffoldX(
            modifier = Modifier
                .focusProperties {
                    canFocus = !uiState.isSelectingMood
                },
            topBar = {
                CreateEntryTopBar(
                    onBackClick = onBackClick
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                CreateEntryTopSection(
                    uiState = uiState,
                    onChangeMoodClick = onChangeMoodClick,
                    onTitleChange = onTitleChange
                )

                CreateEntryMiddleSection(
                    modifier = Modifier
                        .weight(1f),
                    uiState = uiState,
                    onDescriptionChange = onDescriptionChange
                )

                CreateEntryBottomSection(
                    onCancelClick = onCancelClick,
                    uiState = uiState,
                    onSaveEntryClick = onSaveEntryClick
                )
            }
        }
    }
}

@Composable
private fun CreateEntryBottomSection(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    uiState: CreateEntryUiState,
    onSaveEntryClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SecondaryButton(
            modifier = Modifier.weight(2f),
            text = stringResource(R.string.cancel),
            onClick = onCancelClick
        )

        PrimaryButton(
            modifier = Modifier
                .weight(5f),
            text = stringResource(R.string.save),
            icon = null,
            enabled = uiState.validDetails,
            onClick = onSaveEntryClick
        )
    }
}

// region Preview

@Preview(showBackground = true)
@Composable
private fun CreateEntryScreenPreview() {
    EchoJournalTheme {
        CreateEntryScreen(
            uiState = CreateEntryUiState(
                audioRecord = AudioRecord(durationSeconds = 60),
                isSelectingMood = true,
                mood = Mood.Peaceful
            ),
            onBackClick = {},
            onTitleChange = {},
            onDescriptionChange = {},
            onSaveEntryClick = {},
            onCancelClick = {},
            onChangeMoodClick = {},
            onMoodClick = {},
            onSaveMoodClick = {},
            onCancelMoodClick = {}
        )
    }
}

// endregion