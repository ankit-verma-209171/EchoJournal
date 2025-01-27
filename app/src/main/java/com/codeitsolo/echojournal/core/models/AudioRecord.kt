package com.codeitsolo.echojournal.core.models

import androidx.compose.runtime.Immutable
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import java.util.Locale

/**
 * Model representing an audio record.
 *
 * @property duration The duration of the audio record.
 */
@Immutable
@Serializable
data class AudioRecord(
    val durationSeconds: Long = 0,
) {
    /**
     * The duration of the audio record in a human-readable format.
     */
    val duration: String
        get() {
            val hours = durationSeconds / 3600
            val minutes = (durationSeconds % 3600) / 60
            val secs = durationSeconds % 60
            return String.format(
                locale = Locale.getDefault(),
                format = "%02d:%02d:%02d", hours, minutes, secs
            )
        }

    /**
     * The date and time when the audio record was created.
     */
    val createdAt: String = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .format(
            LocalDateTime.Format {
                @OptIn(FormatStringsInDatetimeFormats::class)
                byUnicodePattern("yyyy-MM-dd-HH:mm:ss")
            }
        )

    /**
     * The name of the audio file.
     */
    val fileName: String = "echo-journal-$createdAt.mp3"

    companion object {
        const val DURATION_ZERO = "00:00:00"
    }
}
