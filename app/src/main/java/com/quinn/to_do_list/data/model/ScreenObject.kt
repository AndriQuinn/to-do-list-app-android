package com.quinn.to_do_list.data.model

import androidx.compose.runtime.Composable


data class ScreenObject (
    val name: String,
    val composableContent: @Composable () -> Unit
)