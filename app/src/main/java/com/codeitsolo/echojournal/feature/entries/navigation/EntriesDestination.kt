package com.codeitsolo.echojournal.feature.entries.navigation

import androidx.navigation.NavType
import com.codeitsolo.echojournal.core.models.AudioRecord
import com.codeitsolo.echojournal.navigation.getNavType
import kotlinx.serialization.Serializable
import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Represents Entries feature route.
 */
@Serializable
data object EntriesRoute

/**
 * Represents Entries screen destination.
 */
@Serializable
internal data object Entries

/**
 * Represents Create Entry screen destination.
 */
@Serializable
internal data class CreateEntry(val audioRecord: AudioRecord) {

    companion object {
        fun toTypeMap(): Map<KType, NavType<*>> = mapOf(
            typeOf<AudioRecord>() to getNavType<AudioRecord>()
        )
    }
}