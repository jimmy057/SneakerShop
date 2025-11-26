package com.example.proyectofinal.presentation.login

data class LoginState(
    val usuario: String = "",
    val clave: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)
