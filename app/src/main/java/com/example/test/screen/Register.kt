package com.example.test.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.test.R
import com.example.test.ui.theme.lila
import com.example.test.util.CustomButton
import com.example.test.util.CustomInput
import com.example.test.util.CustomLink
import com.example.test.util.CustomTitle

@Composable
fun Register() {
    Image(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds,
        painter = painterResource(id = R.drawable.header),
        contentDescription = ""
    )
    Column(modifier = Modifier.padding(20.dp)) {
        CustomTitle(title = "Registro")

        CustomInput(
            title = "Correo electrónico",
            imageVector = Icons.Default.Email,
            placeholder = "Ingresar correo electrónico",
            keyboardType = KeyboardType.Email
        )
        CustomInput(
            title = "Número de teléfono",
            imageVector = Icons.Default.Call,
            placeholder = "E j: +54 12345678",
            keyboardType = KeyboardType.Phone
        )
        CustomInput(
            title = "Contraseña",
            imageVector = Icons.Default.Lock,
            placeholder = "abcABC#123",
            keyboardType = KeyboardType.Password
        )

        CustomInput(
            title = "Reingresar contraseña",
            imageVector = Icons.Default.Lock,
            placeholder = "Reingresar contraseña",
            keyboardType = KeyboardType.Password
        )

        CustomButton(text = "Ingresar")

        Box(Modifier.align(Alignment.End)) {
            CustomLink(text = "ya tienes una cuesnta? ", link = "Ingresar ")
        }
    }
}