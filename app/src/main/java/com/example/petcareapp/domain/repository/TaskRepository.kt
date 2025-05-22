package com.example.petcareapp.domain.repository

import com.example.petcareapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun createTask(task: Task): Task
    fun getTasksForPet(petId: String): Flow<List<Task>>
    suspend fun deleteTask(taskId: String)
}
