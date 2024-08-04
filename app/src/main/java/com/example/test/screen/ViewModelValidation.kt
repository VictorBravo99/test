package com.example.test.screen

import androidx.lifecycle.ViewModel
import com.example.test.dataClass.TypeOfError
import com.example.test.dataClass.Validation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun <T> List<T>.emptyList() = this.ifEmpty { null }

class ViewModelValidation : ViewModel() {
    private var _state = MutableStateFlow<List<Validation>>(listOf())
    val state: StateFlow<List<Validation>> get() = _state

    private fun emailValidation(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return emailRegex.matches(email)
    }

    private fun passwordValidation(password: String): Boolean {
        val passwordRegex =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@\$!%*?&#]{8,}$")
        return passwordRegex.matches(password)
    }

    private fun phoneValidation(phone: String): Boolean {
        val phoneRegex =
            Regex("^(\\+\\d{1,3}( )?)?((\\(\\d{2,3}\\))|\\d{2,3})[- .]?\\d{3,4}[- .]?\\d{4}\$")
        return phoneRegex.matches(phone)

    }

    fun validate(
        email: String? = null,
        password: String? = null,
        confirmPassword: String? = null,
        phone: String? = null
    ) {
        val validate = ArrayList<Validation>()

        email?.let {
            if (!emailValidation(it)) validate.add(
                Validation(
                    TypeOfError.EMAIL,
                    "Correo inválido"
                )
            )
        }
        password?.let {
            if (!passwordValidation(it)) validate.add(
                Validation(
                    TypeOfError.PASSWORD,
                    "Contraseña inválida"
                )
            )
        }
        confirmPassword?.let {
            if (password != it) validate.add(
                Validation(
                    TypeOfError.CONFIRM_PASSWORD,
                    "Contraseña no coinciden"
                )
            )
        }

        phone?.let {
            if (!phoneValidation(it)) validate.add(
                Validation(
                    TypeOfError.PHONE,
                    "Número inválido"
                )
            )
        }

        confirmPassword?.let {
            if (it.isBlank()) validate.add(
                Validation(
                    TypeOfError.CONFIRM_PASSWORD,
                    "Rellenar este campo"
                )
            )
        }

        _state.value = validate

    }

}