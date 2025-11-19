package com.example.proyectofinal.domain.usecase

import com.example.proyectofinal.domain.repository.UserRepository
import javax.inject.Inject

class ClearUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() {
        repository.clearUser()
    }
}