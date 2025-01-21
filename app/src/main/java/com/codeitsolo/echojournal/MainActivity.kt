package com.codeitsolo.echojournal

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codeitsolo.echojournal.ui.theme.EchoJournalTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Entry point of the android app
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge(
            // Currently, only light mode is supported
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ),
            // Currently, only light mode is supported
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ),
        )
        setContent {
            EchoJournalTheme {
                EchoJournalApp()
            }
        }
    }
}