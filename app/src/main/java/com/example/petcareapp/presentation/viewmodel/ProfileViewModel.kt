package com.example.petcareapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcareapp.data.local.UserPrefs
import com.example.petcareapp.domain.model.User
import com.example.petcareapp.domain.usecase.GetUserByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userPrefs: UserPrefs,
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadCurrentUser() {
        val userId = userPrefs.getCurrentUserId()
        if (userId != null) {
            viewModelScope.launch {
                _user.value = getUserByIdUseCase(userId)
            }
        }
    }

    fun logout() {
        userPrefs.clear()
        _user.value = null
    }
}
