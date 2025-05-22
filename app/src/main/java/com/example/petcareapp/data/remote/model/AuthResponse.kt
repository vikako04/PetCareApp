package com.example.petcareapp.data.remote.model

data class AuthResponse(
    val id: String,
    val email: String,
    val username: String,
    val accessToken: String
)