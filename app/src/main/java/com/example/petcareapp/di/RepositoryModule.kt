package com.example.petcareapp.di

import com.example.petcareapp.data.repository.UserRepositoryImpl
import com.example.petcareapp.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}