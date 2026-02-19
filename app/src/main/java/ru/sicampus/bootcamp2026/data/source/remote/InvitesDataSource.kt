package ru.sicampus.bootcamp2026.data.source.remote

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2026.data.dto.InviteDto
import ru.sicampus.bootcamp2026.data.dto.InviteStatusDto
import ru.sicampus.bootcamp2026.domain.entities.invite.Invite

class InvitesDataSource {
    suspend fun getInvites(): Result<List<InviteDto>> = withContext(Dispatchers.IO) {
        runCatching {
            val response = Network.client.get("${Network.HOST}/api/invites") {
                addAuthHeader()
            }
            if (response.status != HttpStatusCode.OK) {
                error("Status: ${response.status}")
            }
            response.body()
        }
    }

    suspend fun updateInviteStatus(id: Long, status: InviteStatusDto): Result<Unit> =
        withContext(Dispatchers.IO) {
        runCatching {
            val response = Network.client.patch("${Network.HOST}/api/invites/$id") {
                addAuthHeader()
                setBody(mapOf("status" to status))
            }
            if (response.status != HttpStatusCode.OK) {
                error("Status: ${response.status}")
            }
        }
    }


}