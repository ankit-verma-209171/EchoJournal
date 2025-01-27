package com.codeitsolo.echojournal.feature.entries.components.recordaudio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.core.extensions.message
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.core.models.AudioRecorderStatus
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme

/**
 * A component to show the bottom sheet content for recording entries.
 *
 * @param modifier Modifier for styling the content.
 * @param recorderStatus Status of the recording.
 * @param audioRecordedDuration Duration of the audio recorded.
 * @param onResumeRecordingClick Callback for resume recording click.
 * @param onPauseRecordingClick Callback for pause recording click.
 * @param onCompleteRecordingClick Callback for complete recording click.
 * @param onCancelRecordingClick Callback for cancel recording click.
 */
@Composable
internal fun RecordEntryBottomSheetContent(
    modifier: Modifier = Modifier,
    recorderStatus: AudioRecorderStatus,
    audioRecordedDuration: String,
    onResumeRecordingClick: () -> Unit,
    onPauseRecordingClick: () -> Unit,
    onCompleteRecordingClick: () -> Unit,
    onCancelRecordingClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RecordingDetails(
            recordingStatus = recorderStatus.message,
            audioRecordedDuration = audioRecordedDuration
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 51.dp,
                    bottom = 42.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RecordAudioSecondaryButton(
                backgroundColor = MaterialTheme.colorScheme.errorContainer,
                icon = painterResource(R.drawable.ic_cross),
                iconTint = MaterialTheme.colorScheme.onErrorContainer,
                onClick = {
                    onCancelRecordingClick()
                }
            )
            RecordAudioPrimaryButton(
                isRecording = recorderStatus == AudioRecorderStatus.Recording,
                onClick = {
                    if (recorderStatus == AudioRecorderStatus.Recording) {
                        onCompleteRecordingClick()
                    } else {
                        onResumeRecordingClick()
                    }
                }
            )
            RecordAudioSecondaryButton(
                backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer,
                icon = painterResource(
                    if (recorderStatus == AudioRecorderStatus.Recording) R.drawable.ic_pause
                    else R.drawable.ic_check
                ),
                iconTint = MaterialTheme.colorScheme.primary,
                onClick = {
                    if (recorderStatus == AudioRecorderStatus.Recording) {
                        onPauseRecordingClick()
                    } else {
                        onCompleteRecordingClick()
                    }
                }
            )
        }
    }
}

/**
 * A component to show recording details.
 *
 * @param modifier Modifier for styling the row.
 * @param recordingStatus Status of the recording.
 * @param audioRecordedDuration Duration of the audio recorded.
 */
@Composable
internal fun RecordingDetails(
    modifier: Modifier = Modifier,
    recordingStatus: String,
    audioRecordedDuration: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = recordingStatus,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = audioRecordedDuration,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// region Preview
@Preview(showBackground = true)
@Composable
fun RecordEntryBottomSheetContentPreview() {
    EchoJournalTheme {
        RecordEntryBottomSheetContent(
            recorderStatus = AudioRecorderStatus.Recording,
            audioRecordedDuration = "00:00:00",
            onResumeRecordingClick = {},
            onPauseRecordingClick = {},
            onCompleteRecordingClick = {},
            onCancelRecordingClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecordingDetailsPreview() {
    EchoJournalTheme {
        RecordingDetails(
            recordingStatus = AudioRecorderStatus.Paused.message,
            audioRecordedDuration = AudioRecord.DURATION_ZERO
        )
    }
}

// endregion