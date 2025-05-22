package com.example.petcareapp.data.remote.api


import com.example.petcareapp.data.remote.model.AddPetRequest
import com.example.petcareapp.data.remote.model.AuthResponse
import com.example.petcareapp.data.remote.model.PetResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {

    @POST("/api/auth/register")
    suspend fun register(@Body body: Map<String, String>): AuthResponse

    @POST("/api/auth/login")
    suspend fun login(@Body body: Map<String, String>): AuthResponse

    @POST("/api/pets")
    suspend fun addPet(
        @Header("Authorization") token: String,
        @Body pet: AddPetRequest
    ): PetResponse
}
