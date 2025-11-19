package com.example.proyectofinal.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectofinal.data.local.Dao.UserDao
import com.example.proyectofinal.data.local.Entity.LocalUser

@Database(
    entities = [LocalUser::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
