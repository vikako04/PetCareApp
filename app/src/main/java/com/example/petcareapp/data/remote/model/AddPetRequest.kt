package com.example.petcareapp.data.remote.model

data class AddPetRequest(
    val name: String,
    val type: String,
    val age: Int,
    val description: String,
    val avatar: String
)