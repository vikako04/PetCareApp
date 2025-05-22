package com.example.petcareapp.data.repository

import com.example.petcareapp.data.local.UserPrefs
import com.example.petcareapp.data.local.dao.UserDao
import com.example.petcareapp.data.remote.api.ApiService
import com.example.petcareapp.data.remote.model.AddPetRequest
import com.example.petcareapp.domain.model.Pet
import com.example.petcareapp.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow

class PetRepositoryImpl(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val userPrefs: UserPrefs
) : PetRepository {
    override suspend fun addPet(pet: Pet): Pet {
        val userId = userPrefs.getCurrentUserId() ?: throw Exception("User not logged in")

        val token = userDao.getAccessToken(userId) ?: throw Exception("No token found")

        val request = AddPetRequest(
            name = pet.name,
            type = pet.type,
            age = pet.age,
            description = pet.description,
            avatar = pet.avatar
        )
        val response = apiService.addPet(token = "Bearer $token", request)
        return Pet(
            id = response.id,
            name = response.name,
            type = response.type,
            age = response.age,
            description = response.description ?: "",
            avatar = response.avatar ?: "",
            owner = response.owner
        )
    }

}