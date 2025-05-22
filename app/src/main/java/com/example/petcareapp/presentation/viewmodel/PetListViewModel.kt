package com.example.petcareapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcareapp.domain.model.Pet
import com.example.petcareapp.domain.usecase.GetPetsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PetListViewModel(
    getPetsUseCase: GetPetsUseCase
) : ViewModel() {

    private val _pets = MutableStateFlow<List<Pet>>(emptyList())
    val pets: StateFlow<List<Pet>> = _pets

    init {
        viewModelScope.launch {
            getPetsUseCase().collect {
                _pets.value = it
            }
        }
    }
}