package com.example.petcareapp.data.local.mapper

import com.example.petcareapp.data.local.entity.TaskEntity
import com.example.petcareapp.domain.model.Task

fun TaskEntity.toTask() = Task(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
    dueDate = dueDate,
    pet = pet
)

fun Task.toEntity() = TaskEntity(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
    dueDate = dueDate,
    pet = pet
)
