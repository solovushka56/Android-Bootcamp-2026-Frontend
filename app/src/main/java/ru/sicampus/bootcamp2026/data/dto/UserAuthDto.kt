package ru.sicampus.bootcamp2026.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthDto(
    @SerialName("user") val userDto: UserDto?
//    @SerialName("token") val token: String?,
)