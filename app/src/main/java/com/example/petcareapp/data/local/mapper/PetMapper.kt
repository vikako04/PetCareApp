package com.example.petcareapp.data.local.mapper

import com.example.petcareapp.data.local.entity.PetEntity
import com.example.petcareapp.domain.model.Pet
import java.util.UUID

fun PetEntity.toPet(): Pet {
    return Pet(
        id = id,
        name = name,
        type = type,
        age = age,
        description = description,
        avatar = avatar,
        owner = owner
    )
}

fun Pet.toEntity(): PetEntity {
    return PetEntity(
        id = id?: UUID.randomUUID().toString(),
        name = name,
        type = type,
        age = age,
        description = description,
        avatar = avatar,
        owner = owner
    )
}