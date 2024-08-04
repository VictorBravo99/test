package com.example.test.navegation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.test.dataClass.Screen
import com.example.test.screen.Login
import com.example.test.screen.Register
import com.example.test.screen.ViewModelValidation

@Composable
fun Navegation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        val viewModel = ViewModelValidation()

        composable(Screen.LoginScreen.route) {
            Column {
                Login(viewModel, navController)
            }
        }

        composable(Screen.RegisterScreen.route){
            Column {
                Register(viewModel, navController)
            }
        }

    }
}