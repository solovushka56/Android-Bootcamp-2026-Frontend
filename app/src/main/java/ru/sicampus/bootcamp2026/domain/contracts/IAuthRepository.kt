package ru.sicampus.bootcamp2026.domain.contracts

import ru.sicampus.bootcamp2026.domain.entities.auth.UserAuth

interface IAuthRepository {
    suspend fun checkAndAuth(
        login: String,
        password: String
    ): Result<UserAuth>
    suspend fun tryAuthByToken(): Result<UserAuth>

//    suspend fun login(): Result<Unit>
//    suspend fun logout(): Result<Unit>

}