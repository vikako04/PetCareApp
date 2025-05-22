package com.example.petcareapp.domain.usecase

import com.example.petcareapp.domain.model.Task
import com.example.petcareapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow


class GetTasksByPetUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(petId: String): Flow<List<Task>> {
        return repository.getTasksForPet(petId)
    }
}

