package com.example.petcareapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petcareapp.presentation.screens.HomeScreen
import com.example.petcareapp.presentation.screens.LoginScreen
import com.example.petcareapp.presentation.screens.RegisterScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(
            viewModel = getViewModel(),
            navController = navController) }
        composable("register") { RegisterScreen(
            viewModel = getViewModel(),
            navController = navController) }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("calendar") {
            HomeScreen(navController = navController)
        }
        composable("profile") {
            HomeScreen(navController = navController)
        }
    }
}
