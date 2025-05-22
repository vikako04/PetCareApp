package com.example.petcareapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.petcareapp.data.local.dao.PetDao
import com.example.petcareapp.data.local.dao.TaskDao
import com.example.petcareapp.data.local.dao.UserDao
import com.example.petcareapp.data.local.entity.PetEntity
import com.example.petcareapp.data.local.entity.TaskEntity
import com.example.petcareapp.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, PetEntity::class, TaskEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun petDao(): PetDao
    abstract fun taskDao(): TaskDao
}