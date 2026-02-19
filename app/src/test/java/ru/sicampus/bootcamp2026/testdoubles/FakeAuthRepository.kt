package ru.sicampus.bootcamp2026.testdoubles

import ru.sicampus.bootcamp2026.domain.contracts.IAuthRepository
import ru.sicampus.bootcamp2026.domain.entities.User
import ru.sicampus.bootcamp2026.domain.entities.auth.UserAuth

class FakeAuthRepository(): IAuthRepository {

    class NotStubbed(message: String) : IllegalStateException(message)

    var checkAndAuthResult: Result<UserAuth> =
        Result.failure(Exception("checkAndAuthResult is not set"))
    var tryAuthByTokenResult: Result<UserAuth> =
        Result.failure(Exception("tryAuthByTokenResult is not set"))
    var logoutResult: Result<Unit> = Result.success(Unit)



    override suspend fun checkAndAuth(login: String, password: String): Result<UserAuth> {
        return checkAndAuthResult
    }

    override suspend fun tryAuthByToken(): Result<UserAuth> {
        return tryAuthByTokenResult
    }

    override suspend fun logout(): Result<Unit> {
        return logoutResult
    }
}