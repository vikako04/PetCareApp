package com.example.petcareapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class PetEntity(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "age")
    val age: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "owner")
    val owner: String? = null
)