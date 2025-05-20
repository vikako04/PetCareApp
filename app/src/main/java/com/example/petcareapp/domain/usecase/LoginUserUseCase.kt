package com.example.petcareapp.domain.usecase

import com.example.petcareapp.domain.model.User
import com.example.petcareapp.domain.repository.AuthRepository
import com.example.petcareapp.domain.repository.UserRepository

class LoginUserUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): User {
        val user = authRepository.login(email, password)
        userRepository.saveUser(user)
        return user
    }
}