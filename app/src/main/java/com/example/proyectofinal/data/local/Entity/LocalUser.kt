package com.example.proyectofinal.data.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class LocalUser(
    @PrimaryKey val id: Int,
    val nombre: String,
    val token: String
)