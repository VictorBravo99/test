package com.example.test.util

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.example.test.ui.theme.lila

@Composable
fun CustomLink(text: String = "", link: String, onClick: () -> Unit = {}) {
    Row {
        Text(text = text)
        Text(text = link, color = lila, modifier = Modifier.pointerInput(Unit) {
            detectTapGestures {
                onClick()
            }
        })
    }
}