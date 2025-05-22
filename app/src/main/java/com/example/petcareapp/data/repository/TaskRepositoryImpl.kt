package com.example.petcareapp.data.repository

import com.example.petcareapp.data.local.UserPrefs
import com.example.petcareapp.data.local.dao.TaskDao
import com.example.petcareapp.data.local.entity.TaskEntity
import com.example.petcareapp.data.local.mapper.toEntity
import com.example.petcareapp.data.local.mapper.toTask
import com.example.petcareapp.data.remote.api.ApiService
import com.example.petcareapp.domain.model.Task
import com.example.petcareapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDao: TaskDao,
    private val api: ApiService,
    private val userPrefs: UserPrefs

) : TaskRepository {

    override suspend fun createTask(task: Task): Task {

        val token = userPrefs.getAccessToken() ?: throw Exception("User not logged in")

        // Отправляем на сервер
        val created = api.createTask("Bearer $token", task.pet, task)
        // Сохраняем в Room
        taskDao.insertTask(created.toEntity())
        return created
    }

    override fun getTasksForPet(petId: String): Flow<List<Task>> {
        return taskDao.getTasksForPet(petId).map { list ->
            list.map { it.toTask() }
        }
    }

    override suspend fun deleteTask(taskId: String) {
        // Удаляем с сервера
        api.deleteTask(taskId)
        // Удаляем локально
        taskDao.deleteTask(taskId)
    }
}
