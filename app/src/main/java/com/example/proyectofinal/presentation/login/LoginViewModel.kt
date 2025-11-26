package com.example.proyectofinal.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.data.local.seccion.SessionDataStore
import com.example.proyectofinal.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val session: SessionDataStore
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.UsuarioChanged ->
                _state.value = _state.value.copy(usuario = event.value)

            is LoginEvent.ClaveChanged ->
                _state.value = _state.value.copy(clave = event.value)

            LoginEvent.LoginClicked -> login()
        }
    }

    private fun login() {
        val current = _state.value

        if (current.usuario.isBlank() || current.clave.isBlank()) {
            _state.value = _state.value.copy(error = "Completa los campos")
            return
        }

        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true, error = null)

                val user = loginUseCase(current.usuario, current.clave)

                if (user != null) {
                    session.saveSession(user.token, user.nombre)
                    _state.value = _state.value.copy(success = true, isLoading = false)
                } else {
                    _state.value = _state.value.copy(error = "Usuario o clave incorrectos", isLoading = false)
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message, isLoading = false)
            }
        }
    }
}

