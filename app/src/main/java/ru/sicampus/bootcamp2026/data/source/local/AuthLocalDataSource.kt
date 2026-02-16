package ru.sicampus.bootcamp2026.data.source.local

object AuthLocalDataSource {
//    val TOKEN = stringPreferencesKey("token")
    val TOKEN = ""
//    val _cachedToken by lazy { getToken() }

    suspend fun getToken(): String? {
        return TODO()
    }
    suspend fun setToken(token: String) {
    }
}