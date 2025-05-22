package com.example.petcareapp.domain.model

data class Task(
    val id: String,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
    val dueDate: String?,
    val pet: String
)
