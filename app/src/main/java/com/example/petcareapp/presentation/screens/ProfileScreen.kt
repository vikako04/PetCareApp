package com.example.petcareapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.petcareapp.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val user by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCurrentUser()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Профиль",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            user?.let {
                Text(text = "Имя: ${it.username}")
                Text(text = "Email: ${it.email}")
            } ?: Text(text = "Загрузка пользователя...")

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                viewModel.logout()
                // Перенаправить на экран входа
                navController.navigate("login") {
                    popUpTo("profile") { inclusive = true }
                }
            }) {
                Text("Выйти")
            }
        }
    }
}
