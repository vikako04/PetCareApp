package com.example.petcareapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.petcareapp.presentation.components.BottomNavigationBar
import com.example.petcareapp.presentation.navigation.NavigationGraph

@Composable
fun PetCareApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
            NavigationGraph(navController)
        }
    }
}