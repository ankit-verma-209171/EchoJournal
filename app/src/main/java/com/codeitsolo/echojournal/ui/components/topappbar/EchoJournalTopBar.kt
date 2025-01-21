package com.codeitsolo.echojournal.ui.components.topappbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme

/**
 * Represents the EchoJournal top bar.
 *
 * @param modifier Modifier to be applied to the layout.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EchoJournalTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.your_echojournal),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
    )
}

// region Preview

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EchoJournalTopBarPreview() {
    EchoJournalTheme {
        Surface {
            EchoJournalTopBar()
        }
    }
}

// endregion