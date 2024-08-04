package com.example.test.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.test.R
import com.example.test.util.CustomButton
import com.example.test.util.CustomInput
import com.example.test.util.CustomLink
import com.example.test.util.CustomTitle

@Composable
fun Register(vMValidation: ViewModelValidation) {
    val errors by vMValidation.state.collectAsState()
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordValidate by remember { mutableStateOf("") }
    Image(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds,
        painter = painterResource(id = R.drawable.header),
        contentDescription = ""
    )
    Column(modifier = Modifier.padding(0.dp)) {
        CustomTitle(title = "Registro")

        CustomInput(
            title = "Correo electrónico",
            imageVector = Icons.Default.Email,
            placeholder = "Ingresar correo electrónico",
            keyboardType = KeyboardType.Email,
            messageError = errors.filter { it.typeOfError == TypeOfError.EMAIL }.emptyList()
                ?.get(0)?.message
        ){ email = it }
        CustomInput(
            title = "Número de teléfono",
            imageVector = Icons.Default.Call,
            placeholder = "E j: +54 12345678",
            keyboardType = KeyboardType.Phone,
            messageError = errors.filter { it.typeOfError == TypeOfError.PHONE }.emptyList()
                ?.get(0)?.message
        ){ phone = it }
        CustomInput(
            title = "Contraseña",
            imageVector = Icons.Default.Lock,
            placeholder = "abcABC#123",
            keyboardType = KeyboardType.Password,
            messageError = errors.filter { it.typeOfError == TypeOfError.PASSWORD }.emptyList()
                ?.get(0)?.message
        ){ password = it}

        CustomInput(
            title = "Reingresar contraseña",
            imageVector = Icons.Default.Lock,
            placeholder = "Reingresar contraseña",
            keyboardType = KeyboardType.Password,
            messageError = errors.filter{it.typeOfError == TypeOfError.CONFIRM_PASSWORD }.emptyList()?.get(0)?.message
        ){ passwordValidate = it}

        CustomButton(text = "Ingresar") {
            vMValidation.validate(email, password, passwordValidate, phone)
        }

        Box(Modifier.align(Alignment.End)) {
            CustomLink(text = "ya tienes una cuesnta? ", link = "Ingresar ")
        }
    }
}