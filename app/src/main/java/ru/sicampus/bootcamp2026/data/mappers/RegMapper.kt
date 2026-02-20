package ru.sicampus.bootcamp2026.data.mappers

import ru.sicampus.bootcamp2026.data.dto.UserDto
import ru.sicampus.bootcamp2026.data.dto.UserRegDto
import ru.sicampus.bootcamp2026.domain.entities.auth.UserReg


fun UserRegDto.toDomain(): UserReg? {
    return UserReg(
        user = user?.toDomain() ?: return null,
        password = password ?: return null
    )
}

fun UserReg.toDto(): UserRegDto {
    return UserRegDto(
        user = user.toDto(),
        password = password
    )
}