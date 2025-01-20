package com.codeitsolo.echojournal.ui.theme.color


import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Material theme extensions color.
 */
@Immutable
class MaterialThemeXColor(
    val onPrimaryFixed: Color = ColorExtensionTokens.onPrimaryFixed,
    val surfaceTint12: Color = ColorExtensionTokens.surfaceTint12,
    val surfaceTint15: Color = ColorExtensionTokens.surfaceTint15,
) {

    fun copy(
        onPrimaryFixed: Color = this.onPrimaryFixed,
        surfaceTint12: Color = this.surfaceTint12,
        surfaceTint15: Color = this.surfaceTint15,
    ): MaterialThemeXColor = MaterialThemeXColor(
        onPrimaryFixed = onPrimaryFixed,
        surfaceTint12 = surfaceTint12,
        surfaceTint15 = surfaceTint15,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MaterialThemeXColor) return false

        if (onPrimaryFixed != other.onPrimaryFixed) return false
        if (surfaceTint12 != other.surfaceTint12) return false
        if (surfaceTint15 != other.surfaceTint15) return false

        return true
    }

    override fun hashCode(): Int {
        val result = onPrimaryFixed.hashCode()
        result * 31 + surfaceTint12.hashCode()
        result * 31 + surfaceTint15.hashCode()
        return result
    }

    override fun toString(): String {
        return "Color(\n" +
                "onPrimaryFixed=$onPrimaryFixed,\n" +
                "surfaceTint12=$surfaceTint12,\n" +
                "surfaceTint15=$surfaceTint15,\n" +
                ")\n"
    }
}

/**
 * Local composition for [MaterialThemeXColor].
 */
val LocalMaterialXColor =
    staticCompositionLocalOf { MaterialThemeXColor() }

/**
 * Extended theme color.
 */
val extendedColor = MaterialThemeXColor()