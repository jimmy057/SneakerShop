package com.example.proyectofinal.data.remote.api

import com.example.proyectofinal.data.remote.request.UserRequest
import com.example.proyectofinal.data.remote.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuariosApi {

    @GET("Usuarios")
    suspend fun getUsuarios(): List<UserResponse>

    @POST("Usuarios")
    suspend fun createUsuario(
        @Body request: UserRequest
    ): UserResponse
}

