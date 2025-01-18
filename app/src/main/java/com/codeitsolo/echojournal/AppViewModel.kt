package com.codeitsolo.echojournal

import androidx.lifecycle.ViewModel
import com.codeitsolo.echojournal.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model for the app.
 */
@HiltViewModel
class AppViewModel @Inject constructor(
    navigator: Navigator
) : ViewModel() {

    val navIntents = navigator.intents
}