package com.example.proyectofinal.data.remote.api

import com.example.proyectofinal.data.remote.dto.UsuarioDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuariosApi {

    @GET("api/Usuarios")
    suspend fun getUsuarios(): List<UsuarioDto>

    @POST("api/Usuarios")
    suspend fun createUsuario(@Body usuario: UsuarioDto): UsuarioDto
}
