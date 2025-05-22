package com.example.petcareapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcareapp.domain.model.Pet
import com.example.petcareapp.domain.usecase.AddPetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AddPetState {
    object Idle : AddPetState()
    object Loading : AddPetState()
    object Success : AddPetState()
    data class Error(val message: String) : AddPetState()
}

class AddPetViewModel(
    private val addPetUseCase: AddPetUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AddPetState>(AddPetState.Idle)
    val state: StateFlow<AddPetState> = _state

    fun addPet(pet: Pet) {
        viewModelScope.launch {
            _state.value = AddPetState.Loading
            try {
                addPetUseCase(pet)
                _state.value = AddPetState.Success
            } catch (e: Exception) {
                _state.value = AddPetState.Error(e.message ?: "Ошибка при добавлении питомца")
            }
        }
    }

    fun resetState() {
        _state.value = AddPetState.Idle
    }
}
