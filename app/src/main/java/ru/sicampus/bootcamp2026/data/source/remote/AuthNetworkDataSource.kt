package ru.sicampus.bootcamp2026.data.source.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2026.data.dto.UserAuthDto
import ru.sicampus.bootcamp2026.data.source.local.AuthLocalDataSource

object AuthNetworkDataSource {

    // Result.failure() if incorrect token|credentials
    suspend fun tryAuthByToken(): Result<UserAuthDto> = withContext(Dispatchers.IO) {
        val token = AuthLocalDataSource.getToken()
            ?: return@withContext Result.failure(Exception("No token"))

        runCatching {
            val response: HttpResponse = Network.client.get("${Network.HOST}/api/person/auth") {
                header(HttpHeaders.Authorization, token)
            }

            response.body<UserAuthDto>()

            // возвращать failure по статус кодам todo

            // catching уже ловит все исключения и кидает Result.failure сам,
            // поэтому нет нужды ловить их тут
        }
    }
}