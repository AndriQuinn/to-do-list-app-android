package com.quinn.to_do_list.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NavBar(
    arrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    background: Color = Color.Transparent,
    elements: List< @Composable () -> Unit>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = arrangement,
        modifier = Modifier
            .background(background)
            .fillMaxWidth()
    ) {
        elements.forEach { elements ->
            elements()
        }
    }
}