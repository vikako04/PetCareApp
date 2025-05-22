package com.example.petcareapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.petcareapp.presentation.components.BottomNavigationBar
import com.example.petcareapp.presentation.navigation.NavigationGraph

@Composable
fun PetCareApp() {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val isLoggedIn = currentRoute in listOf("home", "calendar", "profile", "add_pet", "pet_detail/{petId}")

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, isLoggedIn = isLoggedIn)
        }
    ) { innerPadding ->
        Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
            NavigationGraph(navController)
        }
    }
}
