package com.codeitsolo.echojournal.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme
import com.codeitsolo.echojournal.ui.theme.color.Gradient


/**
 * A primary button component.
 *
 * @param modifier The Modifier to be applied to this composable.
 * @param enabled Whether the button is enabled or not.
 * @param text The text to be displayed on the button.
 * @param icon The icon to be displayed on the button.
 * @param onClick The callback to be invoked when the button is clicked.
 */
@Composable
internal fun PrimaryButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    text: String,
    icon: Painter?,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .then(
                if (enabled) {
                    Modifier
                        .background(Gradient.button)
                } else {
                    Modifier
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                }
            )
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        icon?.let {
            Icon(
                painter = it,
                tint = if (enabled) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.outline
                },
                contentDescription = null
            )
        }
        Text(
            text = text,
            color = if (enabled) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.outline
            },
        )
    }
}

/**
 * A secondary button component.
 *
 * @param modifier The Modifier to be applied to this composable.
 * @param text The text to be displayed on the button.
 * @param onClick The callback to be invoked when the button is clicked.
 */
@Composable
internal fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.onPrimaryContainer)
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


// region Preview

@Preview
@Composable
private fun PrimaryButtonPreview() {
    EchoJournalTheme {
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth(),
            enabled = true,
            text = "Button",
            icon = null,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun SecondaryButtonPreview() {
    EchoJournalTheme {
        SecondaryButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Button",
            onClick = {}
        )
    }
}
// endregion