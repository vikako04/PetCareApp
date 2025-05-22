package com.example.petcareapp.domain.repository

import com.example.petcareapp.domain.model.Pet
import kotlinx.coroutines.flow.Flow


interface PetRepository {

    suspend fun addPet(pet: Pet): Pet
}