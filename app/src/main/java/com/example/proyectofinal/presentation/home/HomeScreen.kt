package com.example.proyectofinal.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proyectofinal.domain.model.User

@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val user = viewModel.user.collectAsState().value

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Bienvenido 👋",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (user != null) {
                UserInfoCard(user)
            } else {
                Text("Cargando usuario...")
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    viewModel.logout()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}

@Composable
fun UserInfoCard(user: User) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(Modifier.padding(20.dp)) {
            Text("Nombre: ${user.nombre}", style = MaterialTheme.typography.bodyLarge)
            Text("ID: ${user.id}", style = MaterialTheme.typography.bodyMedium)
            Text("Token:", style = MaterialTheme.typography.bodyMedium)
            Text(user.token, style = MaterialTheme.typography.bodySmall)
        }
    }
}
