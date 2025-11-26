package com.example.proyectofinal.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(state.success) {
        if (state.success) {
            onSuccess()
        }
    }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Iniciar sesión",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = state.usuario,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.UsuarioChanged(it))
                },
                label = { Text("Usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = state.clave,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.ClaveChanged(it))
                },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = { viewModel.onEvent(LoginEvent.LoginClicked) },
                enabled = !state.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Entrar")
                }
            }

            state.error?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(18.dp))

            TextButton(onClick = onNavigateToRegister) {
                Text("Crear cuenta nueva")
            }
        }
    }
}

