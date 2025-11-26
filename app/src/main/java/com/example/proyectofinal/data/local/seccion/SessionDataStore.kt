package com.example.proyectofinal.data.local.seccion

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val DATASTORE_NAME = "session_prefs"
private val Context._dataStore by preferencesDataStore(name = DATASTORE_NAME)

@Singleton
class SessionDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object Keys {
        val TOKEN = stringPreferencesKey("token")
        val NOMBRE = stringPreferencesKey("nombre")
    }

    suspend fun saveSession(token: String, nombre: String) {
        context._dataStore.edit { prefs ->
            prefs[Keys.TOKEN] = token
            prefs[Keys.NOMBRE] = nombre
        }
    }

    suspend fun clearSession() {
        context._dataStore.edit { prefs ->
            prefs.remove(Keys.TOKEN)
            prefs.remove(Keys.NOMBRE)
        }
    }

    val tokenFlow = context._dataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }
        .map { prefs -> prefs[Keys.TOKEN] ?: "" }

    val nombreFlow = context._dataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }
        .map { prefs -> prefs[Keys.NOMBRE] ?: "" }
}