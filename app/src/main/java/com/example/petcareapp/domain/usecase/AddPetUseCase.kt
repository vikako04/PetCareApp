package com.example.petcareapp.domain.usecase



import com.example.petcareapp.domain.model.Pet
import com.example.petcareapp.domain.repository.PetRepository
import com.example.petcareapp.domain.repository.SavePetRepository

class AddPetUseCase(
    private val repository: PetRepository,
    private val savePetRepository: SavePetRepository
) {
    suspend operator fun invoke(pet: Pet): Pet {
        val newPet = repository.addPet(pet)
        savePetRepository.addPet(newPet)
        return newPet
    }
}
