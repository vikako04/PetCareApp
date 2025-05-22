package com.example.petcareapp.domain.usecase

import com.example.petcareapp.domain.model.Pet
import com.example.petcareapp.domain.repository.SavePetRepository
import kotlinx.coroutines.flow.Flow

class GetPetsUseCase(
    private val repository: SavePetRepository
) {
    operator fun invoke(): Flow<List<Pet>> = repository.getAllPets()
}