package com.codeitsolo.echojournal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codeitsolo.echojournal.core.effects.FlowObservableEffect
import com.codeitsolo.echojournal.ui.theme.color.Gradient
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
            Box(
                modifier = Modifier
                    .systemBarsPadding()
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier
                        .background(
                            brush = Gradient.button,
                            shape = ButtonDefaults.shape
                        ),
                    colors = ButtonColors(
                        containerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    onClick = {}
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Hello World!",
                    )
                }
            }
        }
    }
}

/**
 * Represents the example screen.
 */
@Serializable
data object HelloWorld