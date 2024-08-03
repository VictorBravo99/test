package com.example.test.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.lila

@Composable
fun CustomTitle(title:String = "Title"){
    Text(
        modifier = Modifier.padding(bottom = 10.dp),
        text = title,
        style = TextStyle(
            fontSize = 25.sp,
            color = lila,
            fontWeight = FontWeight(500)
        )
    )
    Box(
        modifier = Modifier
            .padding(bottom = 40.dp)
            .height(3.dp)
            .width(50.dp)
            .background(lila)
    )
}