package com.codeitsolo.echojournal.feature.entries.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.codeitsolo.echojournal.feature.entries.EntriesRoute

/**
 * Adds the navigation entries for the "Entries" feature to the navigation graph.
 *
 * This function defines a nested navigation graph for the "Entries" feature, starting at the
 * [Entries] route.
 */
fun NavGraphBuilder.entries() {
    navigation<EntriesRoute>(
        startDestination = Entries
    ) {

        composable<Entries> {
            EntriesRoute()
        }
    }
}
