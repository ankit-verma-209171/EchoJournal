package com.codeitsolo.echojournal.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.codeitsolo.echojournal.ui.theme.color.LocalMaterialXColor
import com.codeitsolo.echojournal.ui.theme.color.MaterialThemeXColor
import com.codeitsolo.echojournal.ui.theme.color.extendedColor
import com.codeitsolo.echojournal.ui.theme.type.LocalMaterialXTypography
import com.codeitsolo.echojournal.ui.theme.type.MaterialThemeXTypography
import com.codeitsolo.echojournal.ui.theme.type.extendedTypography

/**
 * Material extended theme.
 *
 * @param content The content of the extended theme.
 */
@Composable
fun MaterialThemeX(
    content: @Composable () -> Unit,
) {
    val extendedTypography = extendedTypography
    val extendedColor = extendedColor

    CompositionLocalProvider(
        LocalMaterialXTypography provides extendedTypography,
        LocalMaterialXColor provides extendedColor,
        content = content
    )
}

/**
 * An object to access the material theme extensions.
 */
object MaterialThemeX {
    /**
     * The material theme extensions typography.
     */
    val typography: MaterialThemeXTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMaterialXTypography.current

    /**
     * The material theme extensions color.
     */
    val color: MaterialThemeXColor
        @Composable
        @ReadOnlyComposable
        get() = LocalMaterialXColor.current
}

/**
 * Extension property to access the material theme extensions.
 *
 * @receiver The current MaterialTheme
 *
 * @return The material theme extensions
 */
val MaterialTheme.ext: MaterialThemeX
    get() = MaterialThemeX