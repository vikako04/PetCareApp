package com.example.petcareapp.data.repository

import com.example.petcareapp.data.local.UserPrefs
import com.example.petcareapp.data.local.dao.PetDao
import com.example.petcareapp.data.local.entity.PetEntity
import com.example.petcareapp.domain.model.Pet
import com.example.petcareapp.domain.repository.PetRepository
import com.example.petcareapp.domain.repository.SavePetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class SavePetRepositoryImpl(
    private val petDao: PetDao,
    private val userPrefs: UserPrefs
): SavePetRepository {
    override suspend fun addPet(pet: Pet) {
        val owner = pet.owner ?: throw IllegalStateException("Owner ID must not be null")

        val entity = PetEntity(
            id = pet.id ?: UUID.randomUUID().toString(),
            name = pet.name,
            type = pet.type,
            age = pet.age,
            description = pet.description,
            avatar = pet.avatar,
            owner = owner
        )
        petDao.insertPet(entity)
    }

    override fun getAllPets(): Flow<List<Pet>> {
        val userId = userPrefs.getCurrentUserId() ?: throw Exception("User not logged in")

        return petDao.getPetsByOwner(userId).map { list ->
            list.map {
                Pet(
                    id = it.id,
                    name = it.name,
                    type = it.type,
                    age = it.age,
                    description = it.description,
                    avatar = it.avatar,
                    owner = it.owner
                )
            }
        }    }


}