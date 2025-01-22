package com.codeitsolo.echojournal.ui.theme.color

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Light color scheme.
 */
val LightColorScheme = lightColorScheme(
    surface = primary100,
    surfaceVariant = Color(0xFFE1E2EC),
    inverseOnSurface = secondary95,
    onSurface = neutralVariant10,
    onSurfaceVariant = neutralVariant30,
    outline = neutralVariant50,
    outlineVariant = neutralVariant80,
    primary = primary30,
    primaryContainer = primary50,
    onPrimary = primary100,
    onPrimaryContainer = Color(0xFFEEF0FF),
    inversePrimary = secondary80,
    secondary = secondary30,
    secondaryContainer = secondary50,
    background = neutralVariant99,
    onErrorContainer = error20,
    errorContainer = error95,
    onError = error100,
)

/**
 * Dark color scheme.
 *
 * 🚧Currently not supported dark theme!
 */
val DarkColorScheme = LightColorScheme
