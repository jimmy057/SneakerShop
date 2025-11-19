package com.example.proyectofinal.domain.repository

import com.example.proyectofinal.data.local.Entity.LocalUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(user: String, pass: String): LocalUser?
    suspend fun register(user: String, pass: String): LocalUser?
    fun getUserLocal(): Flow<LocalUser?>
    suspend fun clearUser()
}