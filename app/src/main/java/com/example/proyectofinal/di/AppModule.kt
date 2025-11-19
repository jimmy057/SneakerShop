package com.example.proyectofinal.di

import android.content.Context
import androidx.room.Room
import com.example.proyectofinal.data.local.Dao.UserDao
import com.example.proyectofinal.data.local.database.AppDatabase
import com.example.proyectofinal.data.local.seccion.SessionDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase {
        return Room.databaseBuilder(ctx, AppDatabase::class.java, "app_db").build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideSessionDataStore(@ApplicationContext ctx: Context): SessionDataStore =
        SessionDataStore(ctx)

}