package com.codeitsolo.echojournal.core.extensions

import android.content.Context
import android.widget.Toast

/**
 * Show a toast message
 *
 * @param message The message to be shown
 */
fun Context.showToast(message: String) {
    Toast
        .makeText(
            /* context = */ this,
            /* text = */ message,
            /* duration = */ Toast.LENGTH_LONG
        )
        .show()
}
