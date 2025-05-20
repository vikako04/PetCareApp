package com.example.petcareapp.di

import com.example.petcareapp.data.repository.AuthRepositoryImpl
import com.example.petcareapp.data.repository.UserRepositoryImpl
import com.example.petcareapp.domain.repository.AuthRepository
import com.example.petcareapp.domain.repository.UserRepository
import com.example.petcareapp.domain.usecase.LoginUserUseCase
import com.example.petcareapp.domain.usecase.RegisterUserUseCase
import com.example.petcareapp.presentation.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {


    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }

    factory { LoginUserUseCase(get(), get()) }
    factory { RegisterUserUseCase(get(), get()) }

    viewModel { AuthViewModel(get(), get()) }
}

