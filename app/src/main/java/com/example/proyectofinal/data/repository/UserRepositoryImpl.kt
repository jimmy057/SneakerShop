package com.example.proyectofinal.data.repository

import com.example.proyectofinal.data.local.Entity.LocalUser
import com.example.proyectofinal.data.local.Dao.UserDao
import com.example.proyectofinal.data.local.mapper.toLocalUser
import com.example.proyectofinal.data.remote.UsuariosApi
import com.example.proyectofinal.data.remote.request.UserRequest
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
                    it.password == pass.trim()
        }

        return encontrado?.toLocalUser()?.also { userDao.insert(it) }
    }

    override suspend fun register(user: String, pass: String): LocalUser? {
        val request = UserRequest(user.trim(), pass.trim())
        val response = api.createUsuario(request)

        val local = response.toLocalUser()
        userDao.insert(local)

        return local
    }

    override fun getUserLocal(): Flow<LocalUser?> = userDao.getUser()

    override suspend fun clearUser() = userDao.clearUser()
}
