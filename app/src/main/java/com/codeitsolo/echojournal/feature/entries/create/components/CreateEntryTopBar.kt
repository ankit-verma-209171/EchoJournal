package com.codeitsolo.echojournal.feature.entries.create.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme

/**
 * Represents the Create Entry top bar.
 *
 * @param modifier Modifier to be applied to the layout.
 * @param onBackClick Callback for back click.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateEntryTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.new_entry),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onBackClick)
                    .padding(8.dp)
                    .size(24.dp),
                painter = painterResource(R.drawable.ic_back),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = stringResource(R.string.navigate_back_to_home_screen)
            )
        }
    )
}

// region Preview

@Preview
@Composable
private fun CreateEntryTopBarPreview() {
    EchoJournalTheme {
        Surface {
            CreateEntryTopBar(onBackClick = {})
        }
    }
}

// endregion