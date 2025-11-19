package com.example.proyectofinal.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: UserRepository
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
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            val current = _state.value

            try {
                val result = repo.login(current.usuario, current.clave)

                if (result != null) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        success = true
                    )
                } else {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = "Usuario o clave incorrectos"
                    )
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error desconocido"
                )
            }
        }
    }

}
