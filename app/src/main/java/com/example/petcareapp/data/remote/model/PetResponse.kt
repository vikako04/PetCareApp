package com.example.petcareapp.data.remote.model

data class PetResponse(
    val id: String,
    val name: String,
    val type: String,
    val age: Int,
    val description: String?,
    val avatar: String?,
    val owner: String
)