package com.example.test.util

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.R
import com.example.test.ui.theme.lila
import com.example.test.ui.theme.lilaLight

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomInput(
    title:String = "Correo electrónico",
    imageVector: ImageVector = Icons.Default.Email,
    placeholder: String = stringResource(R.string.label_mail),
    keyboardType: KeyboardType = KeyboardType.Password,
    messageError: String? = null,
    value: (String)->Unit ={},
) {
    var isGone by remember { mutableStateOf(keyboardType == KeyboardType.Password) }

    var input by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .padding(bottom = 20.dp)) {

        Text(
            modifier = Modifier.padding(bottom = 2.dp),
            text = title,
            fontSize = 16.sp
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = lilaLight,
                unfocusedContainerColor = lilaLight,
                disabledContainerColor = lilaLight,
                cursorColor = lila,

                focusedIndicatorColor = lila,
            ),
            value = input,
            isError = messageError?.isNotBlank() ?: false,
            visualTransformation =
            if (isGone) PasswordVisualTransformation()
            else VisualTransformation.None,
            onValueChange = {
                input = it
                value(it)   },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
            ),
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val yStart = 0f
                            val yEnd = size.height
                            drawLine(
                                color = lila,
                                start = Offset(size.width, yStart),
                                end = Offset(size.width, yEnd),
                                strokeWidth = strokeWidth
                            )
                        }
                        .padding(5.dp),
                    imageVector = imageVector,
                    contentDescription = null,

                    )
            },
            trailingIcon = {
                if (keyboardType == KeyboardType.Password) {
                    Icon(
                        modifier = Modifier.pointerInput(Unit) {
                            detectTapGestures {
                                isGone = !isGone
                            }
                        },
                        painter = painterResource(
                            id =
                            if (isGone) R.drawable.visibility_off
                            else R.drawable.visibility
                        ),
                        contentDescription = "eye"
                    )
                }
            },
            placeholder = { Text(placeholder) },
        )

        if (messageError != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = messageError,
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }
    }
}