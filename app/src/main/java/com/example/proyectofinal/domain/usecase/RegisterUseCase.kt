package com.example.proyectofinal.domain.usecase

import com.example.proyectofinal.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(user: String, pass: String) = repo.register(user, pass)
}
