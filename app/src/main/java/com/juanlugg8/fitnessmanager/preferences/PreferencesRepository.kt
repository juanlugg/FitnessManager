package com.juanlugg8.fitnessmanager.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class PreferencesRepository(private val dataStore: DataStore<Preferences>) {
    fun savePreference(id: String) {
        runBlocking {
            dataStore.edit { preferences ->
                preferences[PREFERENCES] ?: "none"
            }
        }
    }

    fun getPreference() {
        runBlocking {
            dataStore.data.map { preferences ->
                preferences[PREFERENCES] ?: "none"
            }.first()
        }
    }

    fun saveTheme(theme: String) {
        runBlocking {
            dataStore.edit { preferences ->
                preferences[THEME] = theme ?: "none"
            }
        }

    }

    fun getTheme(): String {
        return runBlocking {
            dataStore.data.map { preferences ->
                preferences[THEME] ?: "none"
            }.first()
        }
    }

    companion object {
        private val PREFERENCES = stringPreferencesKey("preferences")
        private val THEME = stringPreferencesKey("theme")
    }
}