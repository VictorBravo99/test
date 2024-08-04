package com.example.test.screen

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.test.R
import com.example.test.dataClass.Screen
import com.example.test.dataClass.TypeOfError
import com.example.test.util.CustomButton
import com.example.test.util.CustomCheckbox
import com.example.test.util.CustomInput
import com.example.test.util.CustomLink
import com.example.test.util.CustomTitle
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Login(vMValidation: ViewModelValidation, navController: NavHostController) {
    val errors by vMValidation.state.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column {
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
                    keyboardType = KeyboardType.Email,
                    messageError = errors.filter { it.typeOfError == TypeOfError.EMAIL }.emptyList()
                        ?.get(0)?.message
                ) { email = it }

                CustomInput(
                    title = "Contraseña",
                    imageVector = Icons.Default.Lock,
                    placeholder = "abcABC#123",
                    keyboardType = KeyboardType.Password,  messageError = errors.filter { it.typeOfError == TypeOfError.PASSWORD }
                        .emptyList()
                        ?.get(0)?.message
                ) { password = it }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomCheckbox {
                        scope.launch {
                            val action = if (it) "activo" else "desactivo"
                            snackbarHostState.showSnackbar("Se $action Recordar.")
                        }
                    }
                    CustomLink(link = "Contraseña olvidada? ") {
                        scope.launch {
                            snackbarHostState.showSnackbar("Envía a resetear la Contraseña.")
                        }
                    }
                }

                CustomButton(text = "Ingresar") {
                    scope.launch {
                        snackbarHostState.showSnackbar("Se presionó el botón ingresar")
                    }
                    vMValidation.validate(email = email, password = password)
                }

                Box(Modifier.align(Alignment.End)) {
                    CustomLink(text = "no tienes una cuesnta? ", link = "Registrate ") {
                        navController.navigate(Screen.RegisterScreen.route)
                    }

                }
            }
        }
    }
}

