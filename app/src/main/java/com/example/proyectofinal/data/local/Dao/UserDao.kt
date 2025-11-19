package com.example.proyectofinal.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinal.data.local.Entity.LocalUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: LocalUser)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Flow<LocalUser?>

    @Query("DELETE FROM User")
    suspend fun clearUser()

}
