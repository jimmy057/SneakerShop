package com.example.proyectofinal.data.local.seccion

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val DATASTORE_NAME = "session_prefs"

val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class SessionDataStore(private val context: Context) {

    private object Keys {
        val TOKEN = stringPreferencesKey("token")
        val NOMBRE = stringPreferencesKey("nombre")
    }
    suspend fun saveSession(token: String, nombre: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.TOKEN] = token
            prefs[Keys.NOMBRE] = nombre
        }
    }

    val tokenFlow = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { prefs -> prefs[Keys.TOKEN] ?: "" }

    val nombreFlow = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { prefs -> prefs[Keys.NOMBRE] ?: "" }
}