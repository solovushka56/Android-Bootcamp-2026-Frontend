package ru.sicampus.bootcamp2026.data.repos

import ru.sicampus.bootcamp2026.data.source.local.AuthLocalDataSource
import ru.sicampus.bootcamp2026.data.source.remote.AuthNetworkDataSource
import ru.sicampus.bootcamp2026.domain.contracts.IAuthRepository
import ru.sicampus.bootcamp2026.domain.entities.auth.UserAuth

class AuthRepository(
    val authNetworkDataSource: AuthNetworkDataSource,
    val authLocalDataSource: AuthLocalDataSource,
): IAuthRepository {

    override suspend fun checkAndAuth(
        login: String,
        password: String
    ): Result<UserAuth> {
        authNetworkDataSource.checkAndAuth()
    }

}