package com.quinn.to_do_list.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.quinn.to_do_list.utils.singleClick

@Composable
fun GenericButton(
    content: @Composable () -> Unit,
    clickFunction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            clickFunction()
        }.singleClick(),
        colors = buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        )
    ) {
        content()
    }
}