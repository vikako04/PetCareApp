package com.example.petcareapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.petcareapp.domain.model.Pet
import com.example.petcareapp.presentation.viewmodel.PetListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: PetListViewModel = koinViewModel()
) {

    val petList by viewModel.pets.collectAsState(initial = emptyList())

    Scaffold(
        ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {

            item {

                Text(
                    "Ваши питомцы",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        navController.navigate("add_pet")                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
                ) {
                    Text("Добавить питомца",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White)

                }
                Spacer(modifier = Modifier.height(20.dp))


            }

            items(petList) { pet ->
                PetCard(pet = pet)
                Spacer(modifier = Modifier.height(20.dp))

            }
        }
    }
}

@Composable
fun PetCard(pet: Pet) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFDFD)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if (!pet.avatar.isNullOrEmpty()) {
                AsyncImage(
                    model = pet.avatar,
                    contentDescription = "Аватар питомца",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InfoBadge(label = "Тип", value = pet.type)
                    InfoBadge(label = "Возраст", value = pet.age.toString())
                }

            }
        }
    }
}

@Composable
fun InfoBadge(label: String, value: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color(0xFFF1F8E9),
        tonalElevation = 2.dp
    ) {
        Text(
            text = "$label: $value",
            modifier = Modifier
                .padding(horizontal = 17.dp, vertical = 11.dp),
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color =  Color(0xFF81C784),
            )
        )
    }
}
