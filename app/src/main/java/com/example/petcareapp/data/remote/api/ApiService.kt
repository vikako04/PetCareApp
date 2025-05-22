package com.example.petcareapp.data.remote.api


import androidx.room.Query
import com.example.petcareapp.data.remote.model.AddPetRequest
import com.example.petcareapp.data.remote.model.AuthResponse
import com.example.petcareapp.data.remote.model.PetResponse
import com.example.petcareapp.domain.model.Task
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path


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

    @POST("/api/tasks/{petId}")
    suspend fun createTask(
        @Header("Authorization") token: String,
        @Path("petId") petId: String,
        @Body task: Task
    ): Task

    @GET("/api/tasks/{petId}")
    suspend fun getTasks(@Path("petId") petId: String): List<Task>

    @DELETE("/api/tasks/{taskId}")
    suspend fun deleteTask(@Path("taskId") taskId: String)

    @PATCH("/api/tasks/{id}/toggle")
    suspend fun toggleTaskCompletion(
        @Header("Authorization") token: String,
        @Path("id") taskId: String
    ): Task


}
