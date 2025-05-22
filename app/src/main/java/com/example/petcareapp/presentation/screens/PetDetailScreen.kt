package com.example.petcareapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.petcareapp.domain.model.Task
import com.example.petcareapp.presentation.viewmodel.TaskViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PetDetailScreen(
    petId: String,
    navController: NavHostController,
    viewModel: TaskViewModel = koinViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(petId) {
        viewModel.loadTasks(petId)
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Задачи питомца",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )




            Spacer(modifier = Modifier.height(20.dp))

            if (error != null) {
                Text(
                    text = error ?: "Ошибка",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(8.dp)
                )
            }

            LazyColumn(contentPadding = PaddingValues(bottom = 32.dp)) {
                items(tasks) { task ->
                    TaskCard(task = task, onToggle = {
                        viewModel.toggleTaskCompleted(task)
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


@Composable
fun TaskCard(task: Task, onToggle: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFDFD)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )

                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF81C784)
                    )
                )
            }

            if (task.description?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }

            if (!task.dueDate.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                val dateOnly = task.dueDate.take(10) // "YYYY-MM-DD"
                InfoBadge(label = "Срок", value = dateOnly)
            }

            Spacer(modifier = Modifier.height(12.dp))

        }
    }
}
