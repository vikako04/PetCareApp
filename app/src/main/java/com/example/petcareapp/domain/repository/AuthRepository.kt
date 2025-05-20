package com.example.petcareapp.domain.repository

import com.example.petcareapp.domain.model.User

interface AuthRepository {
    suspend fun register(email: String, username: String, password: String): User
    suspend fun login(email: String, password: String): User
}