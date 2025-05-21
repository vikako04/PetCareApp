package com.example.petcareapp.data.remote.api


import retrofit2.http.Body
import retrofit2.http.POST

data class AuthResponse(
    val id: String,
    val email: String,
    val username: String,
    val accessToken: String
)

interface ApiService {

    @POST("/api/auth/register")
    suspend fun register(@Body body: Map<String, String>): AuthResponse

    @POST("/api/auth/login")
    suspend fun login(@Body body: Map<String, String>): AuthResponse

}
