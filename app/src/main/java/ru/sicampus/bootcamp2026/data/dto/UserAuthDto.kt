package ru.sicampus.bootcamp2026.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserAuthDto(
    val token: String?,
    val userId: Long?
)