package com.example.test.dataClass

sealed class Screen(val route:String) {
    data object LoginScreen : Screen("login_Screen")
    data object RegisterScreen : Screen("register_Screen")
}