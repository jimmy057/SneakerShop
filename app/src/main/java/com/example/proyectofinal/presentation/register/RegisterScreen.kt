package com.example.proyectofinal.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.graphics.Color

@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        TextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Usuario") }
        )

        TextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Contraseña") }
        )

        Button(
            onClick = {
                viewModel.register(user, pass, onSuccess)
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Crear Cuenta")
        }

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Volver")
        }

        if (viewModel.errorMessage != null) {
            Text(
                text = viewModel.errorMessage!!,
                color = Color.Red,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}
