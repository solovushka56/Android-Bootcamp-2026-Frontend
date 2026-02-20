package ru.sicampus.bootcamp2026.data.source.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2026.data.dto.MeetDto
import ru.sicampus.bootcamp2026.data.source.local.AuthLocalDataSource

class MeetsDataSource(
    val authLocalDataSource: AuthLocalDataSource
) {
    suspend fun getMeets(): Result<List<MeetDto>> = withContext(Dispatchers.IO) {
        runCatching {
            val result = Network.client.get("${Network.HOST}/api/meets") {
                addAuthHeader(authLocalDataSource)
            }
            if (result.status != HttpStatusCode.OK) {
                error("Status: ${result.status}")
            }
            result.body()
        }
    }
}