package ru.sicampus.bootcamp2026.domain.entities.user

data class User(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val photoUrl: String? = null,
)