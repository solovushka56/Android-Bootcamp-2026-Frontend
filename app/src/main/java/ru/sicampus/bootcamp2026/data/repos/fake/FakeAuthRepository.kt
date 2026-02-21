package ru.sicampus.bootcamp2026.data.repos.fake

import ru.sicampus.bootcamp2026.domain.contracts.IAuthRepository
import ru.sicampus.bootcamp2026.domain.entities.auth.UserAuth

class FakeAuthRepository: IAuthRepository {
    override suspend fun checkAndAuth(
        login: String,
        password: String
    ): Result<UserAuth> {
        TODO("Not yet implemented")
    }

    override suspend fun tryAuthByToken(): Result<UserAuth> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Result<Unit> {
        TODO("Not yet implemented")
    }
}