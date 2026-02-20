package ru.sicampus.bootcamp2026.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRegDto (
    @SerialName("user") val user: UserDto?,
    @SerialName("password") val password: String?
)