package com.codeitsolo.echojournal.feature.entries.components.recordaudio

import android.content.res.Configuration
import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codeitsolo.echojournal.R
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme
import com.codeitsolo.echojournal.ui.theme.color.Gradient
import com.codeitsolo.echojournal.ui.theme.color.primary90
import com.codeitsolo.echojournal.ui.theme.color.primary95

/**
 * A primary button to record audio.
 *
 * @param modifier Modifier for styling the button.
 * @param isRecording Whether the button is in recording state.
 * @param contentDescription Content description for the button.
 * @param onClick Callback for button click.
 */
@Composable
internal fun RecordAudioPrimaryButton(
    modifier: Modifier = Modifier,
    isRecording: Boolean,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    val transition = rememberInfiniteTransition(label = "Record")
    val scalePulse by transition.animateFloat(
        initialValue = if (isRecording) 0f else 0f,
        targetValue = if (isRecording) 1f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        ),
        label = "Scale pulse animation"
    )
    val scaleButton by transition.animateFloat(
        initialValue = if (isRecording) 1f else 1f,
        targetValue = if (isRecording) 1.02f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = EaseInOutBack),
            repeatMode = RepeatMode.Restart
        ),
        label = "Scale button animation"
    )

    Box(
        modifier = Modifier
            .drawBehind(
                onDraw = {
                    drawCircle(
                        color = primary95,
                        radius = 64.dp.toPx() * scalePulse
                    )
                    drawCircle(
                        color = primary90,
                        radius = 54.dp.toPx() * scalePulse
                    )
                }
            )
            .graphicsLayer {
                scaleX = scaleButton
                scaleY = scaleButton
            }
            .size(72.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .background(Gradient.button),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp),
            painter = painterResource(
                if (isRecording) R.drawable.ic_check else R.drawable.ic_mic
            ),
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

/**
 * A secondary button for secondary actions while recording audio.
 *
 * @param modifier Modifier for styling the button.
 * @param size Size of the button.
 * @param shape Shape of the button.
 * @param backgroundColor Background color of the button.
 * @param icon Painter for the icon.
 * @param iconTint Tint color of the icon.
 * @param iconSize Size of the icon.
 * @param contentDescription Content description for the icon.
 * @param onClick Callback for button click.
 */
@Composable
internal fun RecordAudioSecondaryButton(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    shape: Shape = CircleShape,
    backgroundColor: Color,
    icon: Painter,
    iconTint: Color,
    iconSize: Dp = 24.dp,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .clickable(onClick = onClick)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(iconSize),
            painter = icon,
            contentDescription = contentDescription,
            tint = iconTint
        )
    }
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecordAudioPrimaryButtonPreview() {
    EchoJournalTheme {
        Surface(
            modifier = Modifier
                .padding(8.dp),
        ) {
            RecordAudioPrimaryButton(
                isRecording = true,
                contentDescription = "Record Audio",
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecordAudioSecondaryButtonPreview() {
    EchoJournalTheme {
        RecordAudioSecondaryButton(
            modifier = Modifier
                .padding(8.dp),
            backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer,
            icon = painterResource(id = R.drawable.ic_pause),
            iconTint = MaterialTheme.colorScheme.primary,
            onClick = {}
        )
    }
}

// endregion