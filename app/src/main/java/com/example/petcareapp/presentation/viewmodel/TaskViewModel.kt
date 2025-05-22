package com.example.petcareapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcareapp.data.local.UserPrefs
import com.example.petcareapp.domain.model.Task
import com.example.petcareapp.domain.usecase.CreateTaskUseCase
import com.example.petcareapp.domain.usecase.DeleteTaskUseCase
import com.example.petcareapp.domain.usecase.GetTasksByPetUseCase
import com.example.petcareapp.domain.usecase.ToggleTaskCompletedUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTasksForPetUseCase: GetTasksByPetUseCase,
    private val toggleTaskCompletedUseCase: ToggleTaskCompletedUseCase,
    private  val userPrefs: UserPrefs

) : ViewModel() {

    fun getCurrentUserId(): String? {
        return userPrefs.getCurrentUserId()
    }

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadTasks(petId: String) {
        viewModelScope.launch {
            getTasksForPetUseCase(petId)
                .catch { e -> _error.value = e.message }
                .collect { taskList -> _tasks.value = taskList }
        }
    }

    fun createTask(task: Task) {
        viewModelScope.launch {
            try {
                createTaskUseCase(task)
                loadTasks(task.pet) // обновим список
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }


    fun toggleTaskCompleted(task: Task) {
        val updated = task.copy(isCompleted = !task.isCompleted)
        viewModelScope.launch {
            try {
                toggleTaskCompletedUseCase(updated.id, updated.isCompleted)
                loadTasks(updated.pet)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }


}
