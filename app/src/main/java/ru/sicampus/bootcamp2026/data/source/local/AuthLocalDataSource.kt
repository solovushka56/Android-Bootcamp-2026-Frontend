package ru.sicampus.bootcamp2026.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import ru.sicampus.bootcamp2026.App
import kotlin.io.encoding.Base64


object AuthLocalDataSource {
    private var _cacheToken: String? = null
    private val TOKEN_KEY = stringPreferencesKey("token")
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


    suspend fun getToken(): String? {
        if (_cacheToken == null) {
            _cacheToken = App.context.dataStore.data.firstOrNull()?.get(TOKEN_KEY) // или так
        }
        return _cacheToken
    }
    suspend fun setToken(token: String) {
        _cacheToken = token
        App.context.dataStore.edit { settings ->
            settings[TOKEN_KEY] = token
        }
    }

    suspend fun clearToken() {
        App.context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
        }
        _cacheToken = null
    }

    suspend fun clearAllData() {
        _cacheToken = null
        App.context.dataStore.edit { it.clear() }
    }
}