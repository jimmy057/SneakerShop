package com.example.proyectofinal.presentation.register

data class RegisterState(
    val usuario: String = "",
    val clave: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)