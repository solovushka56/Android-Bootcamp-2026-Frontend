package ru.sicampus.bootcamp2026.domain.entities.auth

import ru.sicampus.bootcamp2026.domain.entities.user.User

data class UserAuth(
    val user: User,
//    val token: String,
)