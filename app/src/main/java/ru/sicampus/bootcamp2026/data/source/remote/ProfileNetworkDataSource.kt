package ru.sicampus.bootcamp2026.data.source.remote

import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2026.data.dto.UserDto
import ru.sicampus.bootcamp2026.data.source.local.AuthLocalDataSource

class ProfileNetworkDataSource(
    val authLocalDataSource: AuthLocalDataSource
) {

    suspend fun editProfile(userDto: UserDto): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val response = Network.client.put("${Network.HOST}/api/profile") {
                addAuthHeader(authLocalDataSource)
                setBody(userDto)
            }
            if (response.status != HttpStatusCode.OK) {
                error("Server error ${response.status.value}: can't upd profile")
            }
        }
    }
}