package com.example.petcareapp.data.local.mapper


import com.example.petcareapp.data.local.entity.UserEntity
import com.example.petcareapp.domain.model.User

fun UserEntity.toUser(): User {
    return User(
        id = id,
        email = email,
        username = username,
        accessToken = accessToken
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        email = email,
        username = username,
        accessToken = accessToken
    )
}