package com.example.petcareapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean,

    @ColumnInfo(name = "dueDate")
    val dueDate: String?,

    @ColumnInfo(name = "pet")
    val pet: String
)
