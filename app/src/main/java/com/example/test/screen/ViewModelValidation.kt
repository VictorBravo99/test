package com.example.test.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Validation(
    val typeOfError: TypeOfError,
    val message: String,
)

enum class TypeOfError {
    EMAIL,
    PASSWORD,
    CONFIRM_PASSWORD,
    PHONE
}

fun <T> List<T>.emptyList() = this.ifEmpty { null }


class ViewModelValidation() : ViewModel() {
    private var _state = MutableStateFlow<List<Validation>>(listOf())
    val state: StateFlow<List<Validation>> get() = _state

    fun emailValidation(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return emailRegex.matches(email)
    }

    fun passwordValidation(password: String): Boolean {
        val passwordRegex =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")
        return passwordRegex.matches(password)
    }

    fun phoneValidation(phone:String): Boolean{
        val phoneRegex = Regex("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}\$")
        return phoneRegex.matches(phone)

    }

    fun validate(email: String, password: String, confirmPassword: String, phone: String) {
        val validate = ArrayList<Validation>()

        if (!emailValidation(email))validate.add(Validation(
            TypeOfError.EMAIL,
            "Correo inválido"))

        if (!passwordValidation(password)) validate.add(
            Validation(
                TypeOfError.PASSWORD,
                "Contraseña inválida"
            )
        )
        if (password != confirmPassword) validate.add(
            Validation(
                TypeOfError.CONFIRM_PASSWORD,
                "Contraseña no coinciden"
            )
        )

        if (!phoneValidation(phone)) validate.add(
            Validation(
                TypeOfError.PHONE,
                "Número inválido"
            )
        )

        if (confirmPassword.isBlank())validate.add(
            Validation(
                TypeOfError.CONFIRM_PASSWORD,
                "Rellenar este campo"
            )
        )

        _state.value = validate

    }

}