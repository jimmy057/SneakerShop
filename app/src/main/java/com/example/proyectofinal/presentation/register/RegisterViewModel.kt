package com.example.proyectofinal.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state

    fun onEvent(event: RegisterEvent) {
        when(event) {

            is RegisterEvent.UsuarioChanged ->
                _state.value = _state.value.copy(usuario = event.value)

            is RegisterEvent.ClaveChanged ->
                _state.value = _state.value.copy(clave = event.value)

            RegisterEvent.RegisterClicked -> register()
        }
    }

    private fun register() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val result = registerUseCase(
                _state.value.usuario,
                _state.value.clave
            )

            _state.value = _state.value.copy(
                isLoading = false,
                success = result != null,
                error = if (result == null) "Error al registrar" else null
            )
        }
    }
}

