package ru.sicampus.bootcamp2026.data.source.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2026.data.dto.UserAuthDto
import ru.sicampus.bootcamp2026.data.source.local.AuthLocalDataSource

object AuthNetworkDataSource {
    suspend fun checkAndAuth(): Result<UserAuthDto> = withContext(Dispatchers.IO) {
        runCatching {
            val result = Network.client.get("${Network.HOST}/api/person/login") {
                val token = AuthLocalDataSource.getToken()
                if (token != null) {
                    header(HttpHeaders.Authorization, token)
                } // ???? проверяет токен // todo to extension
            }
            result.body()
        }
    }
}