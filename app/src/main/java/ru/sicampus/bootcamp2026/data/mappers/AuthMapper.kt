package ru.sicampus.bootcamp2026.data.mappers

import ru.sicampus.bootcamp2026.data.dto.UserAuthDto
import ru.sicampus.bootcamp2026.domain.entities.auth.UserAuth

fun UserAuthDto.toDomain(): UserAuth? {
    return UserAuth(
        userId = userId ?: return null,
        token = token ?: return null
    )
}

