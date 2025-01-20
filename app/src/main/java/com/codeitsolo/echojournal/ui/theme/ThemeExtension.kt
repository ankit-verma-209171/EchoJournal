package com.codeitsolo.echojournal.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
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

    CompositionLocalProvider(
        LocalMaterialXTypography provides extendedTypography,
        content = content
    )
}

/**
 * An object to access the material theme extensions.
 */
object MaterialThemeX {
    val typography: MaterialThemeXTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMaterialXTypography.current
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