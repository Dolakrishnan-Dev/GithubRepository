package com.example.githubuserprofile.presentation.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CustomTextViewCompose(
    modifier: Modifier = Modifier,
    label: String,
    content: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = style.copy(fontWeight = FontWeight.SemiBold),
            modifier = modifier
        )

        Text(
            text = content,
            style = style,
            modifier = modifier
        )
    }
}