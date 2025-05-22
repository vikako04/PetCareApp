package com.example.petcareapp.data.repository

import com.example.petcareapp.data.local.dao.UserDao
import com.example.petcareapp.data.local.entity.UserEntity
import com.example.petcareapp.domain.model.User
import com.example.petcareapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun saveUser(user: User) {
        val entity = UserEntity(
            id = user.id,
            email = user.email,
            username = user.username,
            accessToken = user.accessToken
        )
        userDao.insertUser(entity)
    }

    override fun getUser(): Flow<User?> {
        return userDao.getUser().map { entity ->
            entity?.let {
                User(
                    id = it.id,
                    email = it.email,
                    username = it.username,
                    accessToken = it.accessToken
                )
            }
        }
    }

    override suspend fun clearUser() {
        userDao.deleteAllUsers()
    }

    override suspend fun getUserById(id: String): User? {
        val entity = userDao.getUserById(id)
        return entity?.let {
            User(
                id = it.id,
                email = it.email,
                username = it.username,
                accessToken = it.accessToken
            )
        }
    }

}