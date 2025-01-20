package com.codeitsolo.echojournal

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codeitsolo.echojournal.core.effects.FlowObservableEffect
import com.codeitsolo.echojournal.ui.theme.ext
import kotlinx.serialization.Serializable

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
        startDestination = HelloWorld
    ){
        composable<HelloWorld> {
            Text(
                modifier = Modifier
                    .systemBarsPadding(),
                text = "Hello World!",
                style = MaterialTheme.ext.typography.extraSmallHeadline
            )
        }
    }
}

/**
 * Represents the example screen.
 */
@Serializable
data object HelloWorld