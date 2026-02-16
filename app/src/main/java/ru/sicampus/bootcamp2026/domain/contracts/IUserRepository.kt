package ru.sicampus.bootcamp2026.domain.contracts

import ru.sicampus.bootcamp2026.domain.entities.User

interface IUserRepository {
    suspend fun getUsers(): Result<List<User>>
}