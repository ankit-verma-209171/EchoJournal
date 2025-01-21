package com.codeitsolo.echojournal.feature.entries

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codeitsolo.echojournal.R
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

    EntriesScreen(
        modifier = modifier,
        uiState = uiState,
        onCreateRecordClick = viewModel::onCreateRecordClick,
    )
}

/**
 * A standalone screen to show the echo journal entries
 *
 * @param modifier The modifier needed to be applied to the composable
 * @param uiState [EntriesUiState]
 * @param onCreateRecordClick Callback for creating a new record
 */
@Composable
private fun EntriesScreen(
    modifier: Modifier = Modifier,
    uiState: EntriesUiState,
    onCreateRecordClick: () -> Unit,
) {
    GradientScaffoldX(
        modifier = modifier,
        topBar = {
            EchoJournalTopBar()
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .padding(8.dp)
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
    ) { innerPadding ->

        // TODO: Show entries

        NoEntriesPlaceholder(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

/**
 * A placeholder to show when there are no entries
 *
 * @param modifier The modifier needed to be applied to the composable
 */
@Composable
private fun NoEntriesPlaceholder(
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
private fun EntriesScreenPreview() {
    EntriesScreen(
        uiState = EntriesUiState(),
        onCreateRecordClick = {},
    )
}

// endregion