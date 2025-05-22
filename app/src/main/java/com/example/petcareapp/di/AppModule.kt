package com.example.petcareapp.di

import com.example.petcareapp.data.local.UserPrefs
import com.example.petcareapp.data.repository.AuthRepositoryImpl
import com.example.petcareapp.data.repository.PetRepositoryImpl
import com.example.petcareapp.data.repository.SavePetRepositoryImpl
import com.example.petcareapp.data.repository.UserRepositoryImpl
import com.example.petcareapp.domain.repository.AuthRepository
import com.example.petcareapp.domain.repository.PetRepository
import com.example.petcareapp.domain.repository.SavePetRepository
import com.example.petcareapp.domain.repository.UserRepository
import com.example.petcareapp.domain.usecase.AddPetUseCase
import com.example.petcareapp.domain.usecase.GetPetsUseCase
import com.example.petcareapp.domain.usecase.LoginUserUseCase
import com.example.petcareapp.domain.usecase.RegisterUserUseCase
import com.example.petcareapp.presentation.viewmodel.AddPetViewModel
import com.example.petcareapp.presentation.viewmodel.AuthViewModel
import com.example.petcareapp.presentation.viewmodel.PetListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {


    single { UserPrefs(androidContext()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<SavePetRepository> { SavePetRepositoryImpl(get(), get()) }

    single<PetRepository> { PetRepositoryImpl(get(), get(), get()) }

    factory { LoginUserUseCase(get(), get()) }
    factory { RegisterUserUseCase(get(), get()) }
    factory { AddPetUseCase(get(), get()) }
    factory { GetPetsUseCase(get()) }

    viewModel { PetListViewModel(get()) }
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel{ AddPetViewModel(get()) }
}

