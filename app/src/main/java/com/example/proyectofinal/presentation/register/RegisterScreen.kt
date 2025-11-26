package com.example.proyectofinal.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(state.success) {
        if (state.success) {
            onSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear cuenta") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = state.usuario,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.UsuarioChanged(it))
                },
                label = { Text("Usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.clave,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.ClaveChanged(it))
                },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = { viewModel.onEvent(RegisterEvent.RegisterClicked) },
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
                    Text("Crear cuenta")
                }
            }

            state.error?.let {
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
