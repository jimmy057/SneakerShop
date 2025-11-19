package com.example.proyectofinal.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.data.local.Dao.UserDao
import com.example.proyectofinal.data.local.mapper.toDomainUser
import com.example.proyectofinal.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    init {
        viewModelScope.launch {
            userDao.getUser().collect { local ->
                _user.value = local?.toDomainUser()
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userDao.clearUser()
        }
    }
}
