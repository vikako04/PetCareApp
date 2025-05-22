package com.example.petcareapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petcareapp.presentation.screens.AddPetScreen
import com.example.petcareapp.presentation.screens.CalendarScreen
import com.example.petcareapp.presentation.screens.HomeScreen
import com.example.petcareapp.presentation.screens.LoginScreen
import com.example.petcareapp.presentation.screens.PetDetailScreen
import com.example.petcareapp.presentation.screens.ProfileScreen
import com.example.petcareapp.presentation.screens.RegisterScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(viewModel = getViewModel(), navController = navController) }
        composable("register") { RegisterScreen(viewModel = getViewModel(), navController = navController) }
        composable("home") { HomeScreen(navController = navController) }
        composable("calendar") { CalendarScreen(navController = navController) }
        composable("profile") { ProfileScreen(navController = navController) }
        composable("add_pet") { AddPetScreen(navController = navController) }
        composable("pet_detail/{petId}") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetDetailScreen(navController = navController, petId = petId)
        }
    }
}
