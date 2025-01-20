package com.codeitsolo.echojournal.ui.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Gradient colors.
 */
@Immutable
object Gradient {

    // region Bg

    val bg = Brush.verticalGradient(
        colors = listOf(
            secondary90.copy(alpha = 0.4f),
            secondary95.copy(alpha = 0.4f),
        )
    )

    val bgSaturated = Brush.verticalGradient(
        colors = listOf(
            secondary90,
            secondary95,
        )
    )

    // endregion

    // region Button

    val button = Brush.verticalGradient(
        colors = listOf(
            primary60,
            primary50,
        )
    )

    val buttonPressed = Brush.verticalGradient(
        colors = listOf(
            primary60,
            primary40,
        )
    )

    // endregion

    // region Mood

    val stressedGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF69193),
            Color(0xFFED3A3A),
        )
    )

    val sadGradient = Brush
        .verticalGradient(
            colors = listOf(
                Color(0xFF7BBCFA),
                Color(0xFF2993F7),
            )
        )

    val neutralGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFC4F3DB),
            Color(0xFF71EBAC),
        )
    )

    val peacefulGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFCCDEE),
            Color(0xFFF991E0),
        )
    )

    val excitedGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF5CB6F),
            Color(0xFFF6B01A),
        )
    )

    // endregion
}