package com.example.petcareapp.domain.repository

import com.example.petcareapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(user: User)
    fun getUser(): Flow<User?>
    suspend fun clearUser()
}