package com.example.petcareapp.domain.usecase

import com.example.petcareapp.domain.repository.TaskRepository

class ToggleTaskCompletedUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(taskId: String, isCompleted: Boolean) {
        repository.updateTaskCompletion(taskId, isCompleted)
    }
}