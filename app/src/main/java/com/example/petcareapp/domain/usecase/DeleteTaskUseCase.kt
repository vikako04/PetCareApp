package com.example.petcareapp.domain.usecase


import com.example.petcareapp.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(taskId: String) {
        repository.deleteTask(taskId)
    }
}
