package com.codeitsolo.echojournal.feature.entries

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.core.extensions.showToast
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.core.models.AudioRecorderStatus
import com.codeitsolo.echojournal.core.permission.PermissionState
import com.codeitsolo.echojournal.core.permission.rememberPermission
import com.codeitsolo.echojournal.feature.entries.components.placeholder.NoEntriesPlaceholder
import com.codeitsolo.echojournal.feature.entries.components.recordaudio.RecordEntryBottomSheetContent
import com.codeitsolo.echojournal.ui.components.scaffold.GradientScaffoldX
import com.codeitsolo.echojournal.ui.components.topappbar.EchoJournalTopBar
import com.codeitsolo.echojournal.ui.theme.color.Gradient

/**
 * Entries route to show entries screen
 *
 * @param modifier The Modifier to be applied to this composable
 * @param viewModel [EntriesViewModel]
 */
@Composable
internal fun EntriesRoute(
    modifier: Modifier = Modifier,
    viewModel: EntriesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val permission by rememberPermission(android.Manifest.permission.RECORD_AUDIO)
    val context = LocalContext.current
    var pendingCreateRecordTask: Boolean by remember(Unit) { mutableStateOf(false) }

    fun onCreateRecordClick() {
        if (permission.state == PermissionState.DeniedPermanently) {
            context.showToast(message = context.getString(R.string.audio_permission_denied_permanently_rationale))
            return
        }

        if (permission.state == PermissionState.Granted) {
            viewModel.createRecord()
        } else {
            permission.requestPermission()
            pendingCreateRecordTask = true
        }
    }

    LaunchedEffect(permission.state) {
        if (pendingCreateRecordTask) {
            if (permission.state == PermissionState.Granted) {
                viewModel.createRecord()
            } else if (permission.state == PermissionState.DeniedPermanently) {
                context.showToast(message = context.getString(R.string.audio_permission_denied_permanently_rationale))
            } else {
                context.showToast(message = context.getString(R.string.audio_permission_denied_rationale))
            }
            pendingCreateRecordTask = false
        }
    }

    LifecycleStartEffect(Unit) {
        onStopOrDispose {
            if (uiState.currentlyRecordingAudioRecord != null &&
                uiState.audioRecorderStatus == AudioRecorderStatus.Recording
            ) {
                viewModel.onPauseRecordingClick()
            }
        }
    }

    EntriesScreen(
        modifier = modifier,
        uiState = uiState,
        onCreateRecordClick = ::onCreateRecordClick,
        onResumeRecordingClick = viewModel::onResumeRecordingClick,
        onPauseRecordingClick = viewModel::onPauseRecordingClick,
        onCompleteRecordingClick = viewModel::onCompleteRecordingClick,
        onCancelRecordingClick = viewModel::onCancelRecordingClick
    )
}

/**
 * A standalone screen to show the echo journal entries
 *
 * @param modifier The modifier needed to be applied to the composable
 * @param uiState [EntriesUiState]
 * @param onCreateRecordClick Callback for creating a new record
 * @param onResumeRecordingClick Callback for resume recording click.
 * @param onPauseRecordingClick Callback for pause recording click.
 * @param onCompleteRecordingClick Callback for complete recording click.
 * @param onCancelRecordingClick Callback for cancel recording click.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EntriesScreen(
    modifier: Modifier = Modifier,
    uiState: EntriesUiState,
    onCreateRecordClick: () -> Unit,
    onResumeRecordingClick: () -> Unit,
    onPauseRecordingClick: () -> Unit,
    onCompleteRecordingClick: () -> Unit,
    onCancelRecordingClick: () -> Unit,
) {
    val audioDuration = remember(uiState.currentlyRecordingAudioRecord) {
        uiState.currentlyRecordingAudioRecord?.duration ?: AudioRecord.DURATION_ZERO
    }

    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Hidden,
        skipHiddenState = false
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )

    LaunchedEffect(uiState.currentlyRecordingAudioRecord) {
        if (uiState.currentlyRecordingAudioRecord != null) {
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
            RecordEntryBottomSheetContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                recorderStatus = uiState.audioRecorderStatus,
                audioRecordedDuration = audioDuration,
                onResumeRecordingClick = onResumeRecordingClick,
                onPauseRecordingClick = onPauseRecordingClick,
                onCompleteRecordingClick = onCompleteRecordingClick,
                onCancelRecordingClick = onCancelRecordingClick,
            )
        }
    ) {
        GradientScaffoldX(
            topBar = {
                EchoJournalTopBar()
            },
            floatingActionButton = {
                EntriesFloatingActionButton(
                    modifier = Modifier
                        .padding(8.dp),
                    onCreateRecordClick = onCreateRecordClick
                )
            }
        ) { innerPadding ->

            // TODO: Show entries

            NoEntriesPlaceholder(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        }
    }
}

/**
 * A floating action button for entries screen to create new entries.
 *
 * @param modifier Modifier for styling the button.
 * @param onCreateRecordClick Callback for create record click.
 */
@Composable
private fun EntriesFloatingActionButton(
    modifier: Modifier = Modifier,
    onCreateRecordClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(64.dp)
            .background(Gradient.button)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = remember { ripple(bounded = false) },
                onClick = onCreateRecordClick
            ),
        contentAlignment = Alignment.Center,
        content = {
            Icon(
                modifier = Modifier
                    .size(19.dp),
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = stringResource(R.string.create_record),
                tint = Color.White
            )
        }
    )
}

// region Preview

@Preview(showBackground = true)
@Composable
private fun EntriesScreenPreview() {
    EntriesScreen(
        uiState = EntriesUiState(),
        onCreateRecordClick = {},
        onResumeRecordingClick = {},
        onPauseRecordingClick = {},
        onCompleteRecordingClick = {},
        onCancelRecordingClick = {}
    )
}

// endregion