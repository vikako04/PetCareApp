package com.example.petcareapp.domain.usecase


import com.example.petcareapp.domain.model.User
import com.example.petcareapp.domain.repository.UserRepository

class GetUserByIdUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(userId: String): User? {
        return repository.getUserById(userId)
    }
}
