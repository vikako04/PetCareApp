package com.example.petcareapp.domain.usecase

import com.example.petcareapp.domain.model.Task
import com.example.petcareapp.domain.repository.TaskRepository

class CreateTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task): Task {
        return repository.createTask(task)
    }
}
