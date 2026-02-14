package com.quinn.to_do_list.ui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.quinn.to_do_list.R

@Composable
fun AttributionText(
   author: String,
   authorLink: String,
   source: String,
   sourceLink: String
) {
    val uriHandler = LocalUriHandler.current
    val annotatedString = buildAnnotatedString {
        append(stringResource(R.string.icons_made_by_txt_attribution_text))

        // Author link
        pushStringAnnotation(tag = "URL", annotation = authorLink)
        withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
            append(author)
        }
        pop() // finish author link

        append(stringResource(R.string.from_txt_attribution_text))

        // Source link
        pushStringAnnotation(tag = "URL", annotation = sourceLink)
        withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
            append(source)
        }
        pop() // Finish Source link
    }

    ClickableText(
        text = annotatedString,
        style = MaterialTheme.typography.bodyMedium,
        onClick = { offset ->
            // Find which annotation was clicked
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    uriHandler.openUri(annotation.item)
                }
        }
    )
}