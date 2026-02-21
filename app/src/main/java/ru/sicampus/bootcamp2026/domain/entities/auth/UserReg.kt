package ru.sicampus.bootcamp2026.domain.entities.auth

import ru.sicampus.bootcamp2026.domain.entities.user.User

class UserReg (
    val user: User,
    val password: String
)