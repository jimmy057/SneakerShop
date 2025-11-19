package com.example.proyectofinal.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {

    var isLoading = false
        private set

    var errorMessage: String? = null
        private set

    fun register(
        user: String,
        pass: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                isLoading = true
                val result = repo.register(user, pass)

                if (result != null) {
                    onSuccess()
                } else {
                    errorMessage = "No se pudo crear el usuario"
                }
            } catch (e: Exception) {
                errorMessage = e.message ?: "Error desconocido"
            } finally {
                isLoading = false
            }
        }
    }
}