package com.example.petcareapp.domain.repository

import com.example.petcareapp.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface SavePetRepository {
    suspend fun addPet(pet: Pet)

    fun getAllPets(): Flow<List<Pet>>

}