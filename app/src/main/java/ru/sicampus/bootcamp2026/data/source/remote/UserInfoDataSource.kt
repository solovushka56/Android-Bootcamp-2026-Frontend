package ru.sicampus.bootcamp2026.data.source.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2026.data.dto.UserDto

class UserInfoDataSource {
    suspend fun getUsersBySearch(query: String // alya filter
    ): Result<List<UserDto>> = withContext(Dispatchers.IO) {
        runCatching {
            val response = Network.client.get("${Network.HOST}/api/users/search") {
                addAuthHeader()

                url {
                    parameters.append("q", query)
                }
            }
            if (response.status != HttpStatusCode.OK) {
                error("status: ${response.status}")
            }

            response.body()
        }
    }



}