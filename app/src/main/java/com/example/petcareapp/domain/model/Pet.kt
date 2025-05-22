package com.example.petcareapp.domain.model

data class Pet (
    val id: String ?= null,
    val name: String,
    val type: String,
    val age: Int,
    val description: String,
    val avatar: String,
    val owner: String? = null
)