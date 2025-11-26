package com.example.proyectofinal.data.local.mapper

import com.example.proyectofinal.data.local.Entity.LocalUser
import com.example.proyectofinal.data.remote.response.UserResponse
import com.example.proyectofinal.domain.model.User

fun UserResponse.toLocalUser(): LocalUser {
    return LocalUser(
        id = usuarioId,
        nombre = userName,
        token = "token_${usuarioId}_${userName}"
    )
}

fun LocalUser.toDomainUser(): User =
    User(
        id = this.id,
        nombre = this.nombre,
        token = this.token
    )