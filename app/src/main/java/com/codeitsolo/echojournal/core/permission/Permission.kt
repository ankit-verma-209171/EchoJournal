package com.codeitsolo.echojournal.core.permission

import androidx.compose.runtime.Immutable

/**
 * A model representing permission
 *
 * @property name The name of the permission
 * @property state The state of the permission
 * @param onPermissionRequested Callback to request the permission
 */
@Immutable
data class Permission(
    val name: String,
    val state: PermissionState = PermissionState.Unknown,
    private val onPermissionRequested: (() -> Unit)? = null,
) {

    /**
     * Request the permission
     */
    fun requestPermission() {
        onPermissionRequested?.invoke()
    }
}