package com.codeitsolo.echojournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Entry point of the android app
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EchoJournalTheme {
                EchoJournalApp()
            }
        }
    }
}