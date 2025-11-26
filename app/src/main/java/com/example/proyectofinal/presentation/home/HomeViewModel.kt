package com.example.proyectofinal.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.domain.usecase.ClearUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clearUserUseCase: ClearUserUseCase
) : ViewModel() {

    fun logout(onDone: () -> Unit) {
        viewModelScope.launch {
            clearUserUseCase()
            onDone()
        }
    }
}

