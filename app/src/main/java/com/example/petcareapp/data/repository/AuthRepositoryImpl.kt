package com.example.petcareapp.data.repository

import com.example.petcareapp.data.remote.api.ApiService
import com.example.petcareapp.domain.model.User
import com.example.petcareapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {

    override suspend fun register(email: String, username: String, password: String): User {
        val response = apiService.register(
            mapOf(
                "email" to email,
                "username" to username,
                "password" to password
            )
        )

        return User(
            id = response.id,
            email = response.email,
            username = response.username,
            accessToken = response.accessToken
        )
    }

    override suspend fun login(email: String, password: String): User {
        val response = apiService.login(
            mapOf(
                "email" to email,
                "password" to password
            )
        )

        return User(
            id = response.id,
            email = response.email,
            username = response.username,
            accessToken = response.accessToken
        )
    }
}
