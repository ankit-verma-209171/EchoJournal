package com.codeitsolo.echojournal.ui.components.scaffold

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme
import com.codeitsolo.echojournal.ui.theme.color.Gradient

/**
 * ScaffoldX envelops the material3 Scaffold composable with EchoJournal's theme.
 *
 * @param modifier Modifier to be applied to the layout.
 * @param topBar Composable function for the top bar. Defaults to an empty composable.
 * @param bottomBar Composable function for the bottom bar. Defaults to an empty composable.
 * @param snackbarHost Composable function for the snackbar host. Defaults to an empty composable.
 * @param floatingActionButton Composable function for the floating action button. Defaults to an empty composable.
 * @param floatingActionButtonPosition Position of the floating action button. Defaults to [FabPosition.End].
 * @param containerColor Background color of the layout. Defaults to [MaterialTheme.colorScheme.background].
 * @param contentColor Color for the content. Defaults to the appropriate content color for the container.
 * @param contentWindowInsets Window insets for the content area. Defaults to [ScaffoldDefaults.contentWindowInsets].
 * @param content Composable function for the main content, taking [PaddingValues] as a parameter.
 */
@Composable
fun ScaffoldX(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable ((PaddingValues) -> Unit),
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content
    )
}

/**
 * GradientScaffoldX is a gradient-based wrapper around the material3 Scaffold composable.
 *
 * @param modifier Modifier to be applied to the layout.
 * @param topBar Composable function for the top bar. Defaults to an empty composable.
 * @param bottomBar Composable function for the bottom bar. Defaults to an empty composable.
 * @param snackbarHost Composable function for the snackbar host. Defaults to an empty composable.
 * @param floatingActionButton Composable function for the floating action button. Defaults to an empty composable.
 * @param floatingActionButtonPosition Position of the floating action button. Defaults to [FabPosition.End].
 * @param containerBrush Brush for the container background. Defaults to [Gradient.bg].
 * @param contentColor Color for the content. Defaults to the appropriate content color for the container.
 * @param contentWindowInsets Window insets for the content area. Defaults to [ScaffoldDefaults.contentWindowInsets].
 * @param content Composable function for the main content, taking [PaddingValues] as a parameter.
 */
@Composable
fun GradientScaffoldX(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerBrush: Brush = Gradient.bg,
    contentColor: Color = contentColorFor(MaterialTheme.colorScheme.background),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable ((PaddingValues) -> Unit),
) {
    Scaffold(
        modifier = modifier
            .background(containerBrush),
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = Color.Transparent,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content
    )
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScaffoldXPreview() {
    EchoJournalTheme {
        ScaffoldX(
            content = {}
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GradientScaffoldXPreview() {
    EchoJournalTheme {
        GradientScaffoldX(
            containerBrush = Gradient.bgSaturated,
            content = {}
        )
    }
}

// endregion