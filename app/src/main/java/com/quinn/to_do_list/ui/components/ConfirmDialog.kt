package com.quinn.to_do_list.ui.components


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.quinn.to_do_list.R

@Composable
fun ConfirmDialog(
    title: String,
    message: String,
    background: Color = Color.White,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        containerColor = background,
        onDismissRequest = onCancel,
        title = { Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        ) },
        text = { Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        ) },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = buttonColors(
                    containerColor = Color(0xFFED4845)
                )
            ) {
                Text(
                    text = stringResource(R.string.confirm_label_dialog),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text(
                    text = stringResource(R.string.cancel_label_dialog),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black
                )
            }
        }
    )
}