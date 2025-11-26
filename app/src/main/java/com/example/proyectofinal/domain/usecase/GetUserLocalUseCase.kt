package com.example.proyectofinal.domain.usecase

import com.example.proyectofinal.domain.repository.UserRepository
import javax.inject.Inject

class GetUserLocalUseCase @Inject constructor(
    private val repo: UserRepository
) {
    operator fun invoke() = repo.getUserLocal()
}
