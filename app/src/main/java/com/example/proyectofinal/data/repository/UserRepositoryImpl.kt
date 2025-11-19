package com.example.proyectofinal.data.repository

import com.example.proyectofinal.data.local.Entity.LocalUser
import com.example.proyectofinal.data.local.Dao.UserDao
import com.example.proyectofinal.data.local.mapper.toLocalUser
import com.example.proyectofinal.data.remote.api.UsuariosApi
import com.example.proyectofinal.data.remote.dto.UsuarioDto
import com.example.proyectofinal.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UsuariosApi,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun login(user: String, pass: String): LocalUser? {
        val usuarios = api.getUsuarios()

        val encontrado = usuarios.find {
            it.userName.equals(user.trim(), ignoreCase = true) &&
                    it.password.trim() == pass.trim()
        }

        return encontrado?.toLocalUser()?.also { local ->
            userDao.insert(local)
        }
    }

    override suspend fun register(user: String, pass: String): LocalUser? {
        val nuevo = UsuarioDto(
            usuarioId = 0,
            userName = user.trim(),
            password = pass.trim()
        )

        val creado = api.createUsuario(nuevo)
        val local = creado.toLocalUser()

        userDao.insert(local)
        return local
    }


    override fun getUserLocal(): Flow<LocalUser?> =
        userDao.getUser()

    override suspend fun clearUser() =
        userDao.clearUser()
}