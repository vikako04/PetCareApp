package com.example.petcareapp.domain.usecase

import com.example.petcareapp.domain.model.Task
import com.example.petcareapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksForPetUseCase(private val repository: TaskRepository) {
    operator fun invoke(petId: String): Flow<List<Task>> {
        return repository.getTasksForPet(petId)
    }
}
