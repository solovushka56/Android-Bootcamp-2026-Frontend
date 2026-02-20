package ru.sicampus.bootcamp2026.data.source.remote

import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMessageBuilder
import ru.sicampus.bootcamp2026.data.source.local.AuthLocalDataSource

//authentication
suspend fun HttpMessageBuilder.addAuthHeader(authLocalDataSource: AuthLocalDataSource) {
    val token = authLocalDataSource.getToken() ?: return
    header(HttpHeaders.Authorization, token)
}