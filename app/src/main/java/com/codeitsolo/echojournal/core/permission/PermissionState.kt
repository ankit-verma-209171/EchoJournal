package com.codeitsolo.echojournal.core.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LifecycleResumeEffect

/**
 * Get permission state for the given permission
 *
 * @param permission The permission name Eg. [android.Manifest.permission.RECORD_AUDIO]
 */
@Composable
fun rememberPermission(
    permission: String,
): State<Permission> {
    val context = LocalContext.current
    val activity = LocalActivity.current

    var permissionRequest by remember(Unit) { mutableStateOf<PermissionRequest?>(null) }
    var currentPermission = remember(Unit) {
        mutableStateOf(
            Permission(
                name = permission,
                state = getDefaultState(
                    context = context,
                    permission = permission,
                    activity = activity
                ),
                onPermissionRequested = {
                    permissionRequest = PermissionRequest()
                }
            )
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        val updatedPermissionState = if (granted) {
            PermissionState.Granted
        } else {
            getPermissionStateWhenPermissionIsDenied(
                currentPermissionState = currentPermission.value.state,
                activity = activity,
                permission = permission
            )
        }
        currentPermission.value = currentPermission.value.copy(state = updatedPermissionState)
        permissionRequest = null
    }

    LifecycleResumeEffect(Unit) {
        onPauseOrDispose {
            currentPermission.value = currentPermission.value.copy(
                state = getDefaultState(
                    context = context,
                    permission = permission,
                    activity = activity
                )
            )
        }
    }

    LaunchedEffect(permissionRequest) {
        if (permissionRequest != null) {
            permissionLauncher.launch(permission)
        }
    }

    return currentPermission
}

/**
 * Get permission state when permission is denied
 *
 * @param currentPermissionState The current permission state
 * @param activity The activity
 * @param permission The permission name Eg. [android.Manifest.permission.RECORD_AUDIO]
 *
 * @return [PermissionState]
 */
private fun getPermissionStateWhenPermissionIsDenied(
    currentPermissionState: PermissionState,
    activity: Activity?,
    permission: String
): PermissionState = when (currentPermissionState) {
    PermissionState.Unknown -> {
        val isPermissionPermanentlyDenied =
            activity != null && !ActivityCompat.shouldShowRequestPermissionRationale(
                /* activity = */ activity,
                /* permission = */ permission
            )
        if (isPermissionPermanentlyDenied) PermissionState.DeniedPermanently
        else PermissionState.Denied
    }

    PermissionState.Granted -> PermissionState.Denied
    PermissionState.Denied -> PermissionState.DeniedPermanently
    PermissionState.DeniedPermanently -> PermissionState.DeniedPermanently
}

/**
 * Get default permission state
 *
 * @param context The context
 * @param permission The permission name Eg. [android.Manifest.permission.RECORD_AUDIO]
 * @param activity The activity
 *
 * @return [PermissionState]
 */
private fun getDefaultState(
    context: Context,
    permission: String,
    activity: Activity?
): PermissionState = when {
    ContextCompat.checkSelfPermission(
        /* context = */ context,
        /* permission = */ permission
    ) == PackageManager.PERMISSION_GRANTED -> PermissionState.Granted

    activity != null && ActivityCompat.shouldShowRequestPermissionRationale(
        /* activity = */ activity,
        /* permission = */ permission
    ) -> PermissionState.Denied

    else -> PermissionState.Unknown
}

/**
 * A model representing permission request
 */
@Immutable
enum class PermissionState {
    /**
     * Permission state cannot be determined
     */
    Unknown,

    /**
     * Permission has been granted
     */
    Granted,

    /**
     * Permission has been denied
     */
    Denied,

    /**
     * Permission has been denied permanently
     */
    DeniedPermanently;
}

/**
 * A model representing permission request
 */
@Immutable
private class PermissionRequest