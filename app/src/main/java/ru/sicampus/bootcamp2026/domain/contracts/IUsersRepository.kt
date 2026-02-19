package ru.sicampus.bootcamp2026.domain.contracts

import ru.sicampus.bootcamp2026.domain.entities.User

interface IUsersRepository {
    suspend fun findUsers(query: String): Result<List<User>>
//    suspend fun findUsersByPosition(position: String): Result<List<User>>
    // todo position to enum/model
}