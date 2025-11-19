package com.example.proyectofinal.presentation.login

sealed class LoginEvent {
    data class UsuarioChanged(val value: String) : LoginEvent()
    data class ClaveChanged(val value: String) : LoginEvent()
    object LoginClicked : LoginEvent()
}
