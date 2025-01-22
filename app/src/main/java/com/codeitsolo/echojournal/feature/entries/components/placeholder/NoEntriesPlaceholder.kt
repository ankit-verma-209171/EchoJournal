package com.codeitsolo.echojournal.feature.entries.components.placeholder

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme

/**
 * A placeholder to show when there are no entries
 *
 * @param modifier The modifier needed to be applied to the composable
 */
@Composable
internal fun NoEntriesPlaceholder(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Image(
            modifier = Modifier
                .padding(bottom = 34.dp),
            painter = painterResource(R.drawable.ic_no_entries),
            contentDescription = null
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.no_entries),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(R.string.start_recording_your_first_echo),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(
            modifier = Modifier
                .weight(2f)
        )
    }
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun NoEntriesPlaceholderPreview() {
    EchoJournalTheme {
        NoEntriesPlaceholder(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

// endregion