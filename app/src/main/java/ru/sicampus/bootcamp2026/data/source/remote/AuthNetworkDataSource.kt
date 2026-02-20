package ru.sicampus.bootcamp2026.data.source.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2026.data.dto.UserAuthDto
import ru.sicampus.bootcamp2026.data.dto.UserDto
import ru.sicampus.bootcamp2026.data.dto.UserRegDto
import ru.sicampus.bootcamp2026.data.source.local.AuthLocalDataSource

class AuthNetworkDataSource(
    val authLocalDataSource: AuthLocalDataSource
) {

    suspend fun register(userRegDto: UserRegDto): Result<UserAuthDto> = withContext(Dispatchers.IO) {
        if (authLocalDataSource.hasToken()) // fail if already logged in
            return@withContext Result.failure(Exception("Already registered"))

        runCatching {
            val response = Network.client.post("${Network.HOST}/api/register") {
                setBody(userRegDto)
            }
            if (!response.status.isSuccess()) error("error ${response.status}")

            response.body()
        }
    }


    suspend fun tryAuthByToken(): Result<UserAuthDto> = withContext(Dispatchers.IO) {
        if (!authLocalDataSource.hasToken())
            return@withContext Result.failure(Exception("No token"))

        runCatching {
            val response: HttpResponse = Network.client.get("${Network.HOST}/api/person/auth") {
                addAuthHeader(authLocalDataSource)
            }
            response.body<UserAuthDto>()
            // возвращать failure по статус кодам todo
            // catching уже ловит все исключения и кидает Result.failure сам,
            // поэтому нет нужды ловить их тут
        }
    }
}