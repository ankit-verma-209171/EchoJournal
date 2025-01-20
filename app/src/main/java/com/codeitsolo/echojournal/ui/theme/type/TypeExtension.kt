package com.codeitsolo.echojournal.ui.theme.type


import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

/**
 * Material theme extensions typography.
 */
@Immutable
class MaterialThemeXTypography(
    val extraSmallHeadline: TextStyle = TypeExtensionTokens.extraSmallHeadline,
    val buttonLarge: TextStyle = TypeExtensionTokens.buttonLarge,
    val button: TextStyle = TypeExtensionTokens.button
) {

    fun copy(
        extraSmallHeadline: TextStyle = this.extraSmallHeadline,
        buttonLarge: TextStyle = this.buttonLarge,
        button: TextStyle = this.button
    ): MaterialThemeXTypography = MaterialThemeXTypography(
        extraSmallHeadline = extraSmallHeadline,
        buttonLarge = buttonLarge,
        button = button
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MaterialThemeXTypography) return false

        if (extraSmallHeadline != other.extraSmallHeadline) return false
        if (buttonLarge != other.buttonLarge) return false
        if (button != other.button) return false

        return true
    }

    override fun hashCode(): Int {
        val result = extraSmallHeadline.hashCode()
        result * 31 + buttonLarge.hashCode()
        result * 31 + button.hashCode()
        return result
    }

    override fun toString(): String {
        return "Typography(\n" +
                "extraSmallHeadline=$extraSmallHeadline,\n" +
                "buttonLarge=$buttonLarge,\n" +
                "button=$button,\n" +
                ")\n"
    }
}

/**
 * Local composition for [MaterialThemeXTypography].
 */
val LocalMaterialXTypography =
    staticCompositionLocalOf { MaterialThemeXTypography() }

/**
 * Extended theme typography.
 */
val extendedTypography = MaterialThemeXTypography()