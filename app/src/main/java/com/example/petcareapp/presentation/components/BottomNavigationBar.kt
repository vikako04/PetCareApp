package com.example.petcareapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

data class NavigationItem(val route: String, val label: String, val icon: ImageVector)

@Composable
fun BottomNavigationBar(navController: NavHostController, isLoggedIn: Boolean) {
    val items = if (isLoggedIn) {
        listOf(
            NavigationItem("home", "Питомцы", Icons.Default.Pets),
            NavigationItem("calendar", "Календарь", Icons.Default.DateRange),
            NavigationItem("profile", "Профиль", Icons.Default.Person),

        )
    } else {
        listOf(
            NavigationItem("login", "Вход", Icons.Default.Login),
            NavigationItem("register", "Регистрация", Icons.Default.PersonAdd)
        )
    }

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = Color(0xFFF1F8E9),
        tonalElevation = 4.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFFFFFF),
                    selectedTextColor = Color(0xFF81C784),
                    indicatorColor = Color(0xFF81C784)
                )
            )
        }
    }
}

