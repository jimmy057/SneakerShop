package com.example.proyectofinal.domain.usecase

import com.example.proyectofinal.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(user: String, pass: String) = repo.login(user, pass)
}
