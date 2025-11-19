package com.example.proyectofinal.presentation.register

sealed class RegisterEvent {
    data class UsuarioChanged(val value: String) : RegisterEvent()
    data class ClaveChanged(val value: String) : RegisterEvent()
    object RegisterClicked : RegisterEvent()
}