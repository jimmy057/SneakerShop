package com.example.proyectofinal.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {
    Column {

        Text("Bienvenido!", style = MaterialTheme.typography.headlineMedium)

        Button(onClick = { viewModel.logout(onLogout) }) {
            Text("Cerrar sesión")
        }
    }
}

