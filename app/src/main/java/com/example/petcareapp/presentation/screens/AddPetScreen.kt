@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.petcareapp.presentation.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun AddPetScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("") }
    var otherType by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri -> imageUri = uri }

    val petTypes = listOf(
        "Собака",
        "Кошка",
        "Хомяк",
        "Рыбки",
        "Кролик",
        "Черепаха",
        "Морская свинка",
        "Крыса",
        "Хорёк",
        "Змея",
        "Ящерица",
        "Попугай",
        "Еж",
        "Шиншилла",
        "Другое"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Добавить питомца",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Фото питомца
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .clickable { launcher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context)
                            .data(imageUri)
                            .build()
                    ),
                    contentDescription = "Фото питомца",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AddAPhoto,
                    contentDescription = "Добавить фото",
                    tint = Color.Gray,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Имя питомца
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя питомца") },
            leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF81C784),
                focusedLabelColor = Color(0xFF81C784)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Возраст питомца (только цифры)
        OutlinedTextField(
            value = age,
            onValueChange = { age = it.filter { it.isDigit() } },
            label = { Text("Возраст") },
            leadingIcon = { Icon(Icons.Default.Cake, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF81C784),
                focusedLabelColor = Color(0xFF81C784)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Тип питомца с выпадающим списком
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = if (selectedType != "Другое") selectedType else otherType,
                onValueChange = {},
                label = { Text("Тип животного") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                leadingIcon = { Icon(Icons.Default.Pets, contentDescription = null) },

                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Выбрать тип",
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
                petTypes.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type, color = Color.Black) },
                        onClick = {
                            selectedType = type
                            expanded = false
                            if (type != "Другое") otherType = ""
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        if (selectedType == "Другое") {
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = otherType,
                onValueChange = { otherType = it },
                leadingIcon = { Icon(Icons.Default.Pets, contentDescription = null) },

                label = { Text("Введите тип животного") },
                modifier = Modifier.fillMaxWidth(),
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
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Описание питомца
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Расскажите о своем питомце") },
            leadingIcon =
            { Icon(Icons.Default.BorderColor,
                    contentDescription = null,
                    modifier = Modifier
                        .offset(y = (-27).dp))
            },
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

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // TODO: отправка данных на сервер или в ViewModel
                val finalType = if (selectedType == "Другие") otherType else selectedType
                // Используй finalType вместе с остальными данными
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
        ) {
            Text(
                "Сохранить",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}
