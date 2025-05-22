@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.petcareapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.petcareapp.domain.model.Pet
import com.example.petcareapp.domain.model.Task
import com.example.petcareapp.presentation.viewmodel.PetListViewModel
import com.example.petcareapp.presentation.viewmodel.TaskViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun CalendarScreen(
    navController: NavHostController,
    petListViewModel: PetListViewModel = koinViewModel(),
    taskViewModel: TaskViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val pets by petListViewModel.pets.collectAsState(initial = emptyList())

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") } // YYYY-MM-DD
    var selectedPet by remember { mutableStateOf<Pet?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val userId = taskViewModel.getCurrentUserId()

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Новая задача",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Название задачи
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Название") },
            leadingIcon = { Icon(Icons.Default.List, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF81C784),
                focusedLabelColor = Color(0xFF81C784)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Описание задачи
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание") },
            leadingIcon = {
                Icon(Icons.Default.BorderColor,
                    contentDescription = null,
                    modifier = Modifier
                        .offset(y = (-27).dp)) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF81C784),
                focusedLabelColor = Color(0xFF81C784)
            ),
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(16.dp))



        var showDatePicker by remember { mutableStateOf(false) }
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

// Блок выбора даты
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true }
        ) {
            OutlinedTextField(
                value = dueDate,
                onValueChange = {},
                label = { Text("Дата выполнения") },
                leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null) },
                singleLine = true,
                readOnly = true,
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = Color.Black,
                    disabledBorderColor = Color.Gray,
                    disabledLabelColor = Color.Black,
                    disabledLeadingIconColor = Color.DarkGray,
                )
            )
        }


        if (showDatePicker) {
            val datePickerState = rememberDatePickerState()

            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val selectedDate = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            dueDate = selectedDate.format(dateFormatter)
                        }
                        showDatePicker = false
                    }) {
                        Text("ОК")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Отмена")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Выбор питомца с DropdownMenu
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedPet?.name ?: "Выберите питомца",
                onValueChange = {},
                label = { Text("Питомец") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                leadingIcon = { Icon(Icons.Default.Pets, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Выбрать питомца",
                            tint = Color(0xFF81C784)
                        )
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF81C784),
                    focusedLabelColor = Color(0xFF81C784),
                    unfocusedBorderColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color(0xFF81C784)
                ),
                singleLine = true
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp))
            ) {
                pets.forEach { pet ->
                    DropdownMenuItem(
                        text = { Text(pet.name, color = Color.Black) },
                        onClick = {
                            selectedPet = pet
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val petId = selectedPet?.id
                if (title.isBlank()) {
                    errorMessage = "Введите название задачи"
                    return@Button
                }
                if (petId == null) {
                    errorMessage = "Выберите питомца"
                    return@Button
                }
                if (userId == null) {
                    errorMessage = "Пользователь не найден"
                    return@Button
                }

                isLoading = true
                errorMessage = null

                val newTask = Task(
                    id = "", // ID сгенерируется на сервере
                    title = title.trim(),
                    description = description.trim(),
                    isCompleted = false,
                    dueDate = dueDate.trim(),
                    pet = petId
                )
                taskViewModel.createTask(newTask)


                isLoading = false
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
        ) {
            Text(
                "Создать задачу",
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
        }

        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(color = Color(0xFF81C784))
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = Color.Red, fontSize = 14.sp)
        }
    }
}
