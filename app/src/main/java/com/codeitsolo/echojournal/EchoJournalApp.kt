package com.codeitsolo.echojournal

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.codeitsolo.echojournal.core.effects.FlowObservableEffect
import com.codeitsolo.echojournal.feature.entries.navigation.EntriesRoute
import com.codeitsolo.echojournal.feature.entries.navigation.entries

/**
 * Represents the EchoJournal app.
 */
@Composable
fun EchoJournalApp() {

    val viewModel: AppViewModel = hiltViewModel()
    val navController = rememberNavController()

    FlowObservableEffect(viewModel.navIntents) { intent ->
        navController.intent()
    }

    NavHost(
        navController = navController,
        startDestination = EntriesRoute
    ) {
        entries()
    }
}