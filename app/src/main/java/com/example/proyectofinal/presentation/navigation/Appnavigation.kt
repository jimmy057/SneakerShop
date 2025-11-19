package com.example.proyectofinal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.presentation.home.HomeScreen
import com.example.proyectofinal.presentation.login.LoginScreen
import com.example.proyectofinal.presentation.register.RegisterScreen

@Composable
fun AppNav() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = "login") {

        composable("login") {
            LoginScreen(
                onNavigateToRegister = { nav.navigate("register") },
                onSuccess = { nav.navigate("home") }
            )
        }

        composable("register") {
            RegisterScreen(
                onBack = { nav.popBackStack() },
                onSuccess = { nav.navigate("home") }
            )
        }

        composable("home") {
            HomeScreen(
                onLogout = {
                    nav.popBackStack()
                    nav.navigate("login")
                }
            )
        }
    }
}