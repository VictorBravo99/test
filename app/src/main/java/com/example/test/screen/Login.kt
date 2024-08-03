package com.example.test.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import com.example.test.util.CustomCheckbox
import com.example.test.util.CustomInput
import com.example.test.util.CustomLink
import com.example.test.util.CustomTitle

@Composable
fun Login(){
    Image(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds,
        painter = painterResource(id = R.drawable.header),
        contentDescription = ""
    )
    Column(modifier = Modifier.padding(20.dp)) {
        CustomTitle(title = "Ingreso")

        CustomInput(
            title = "Correo electrónico",
            imageVector = Icons.Default.Email,
            placeholder = "Ingresar correo electrónico",
            keyboardType = KeyboardType.Email
        )

        CustomInput(
            title = "Contraseña",
            imageVector = Icons.Default.Lock,
            placeholder = "abcABC#123",
            keyboardType = KeyboardType.Password
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            CustomCheckbox()
            CustomLink(link = "Contraseña olvidada? ")


        }


        CustomButton(text = "Ingresar")

        Box(Modifier.align(Alignment.End)) {
            CustomLink(text = "no tienes una cuesnta? ", link = "Registrate ")

        }
    }
}