package com.codeitsolo.echojournal

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Represents the EchoJournal app
 */
@Composable
fun EchoJournalApp() {

    val viewModel: AppViewModel = hiltViewModel()

    Text(
        modifier = Modifier
            .systemBarsPadding(),
        text = "Hello World!"
    )
}
