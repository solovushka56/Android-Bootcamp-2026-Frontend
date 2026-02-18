package ru.sicampus.bootcamp2026.data.repos

import ru.sicampus.bootcamp2026.data.mappers.toDomain
import ru.sicampus.bootcamp2026.data.source.local.AuthLocalDataSource
import ru.sicampus.bootcamp2026.data.source.remote.AuthNetworkDataSource
import ru.sicampus.bootcamp2026.domain.contracts.IAuthRepository
import ru.sicampus.bootcamp2026.domain.entities.auth.UserAuth
import kotlin.io.encoding.Base64

// Сейчас нужно определиться, какой у нас тип авторизации
// Т.е. какой токен. Так мы поймём, делать ген токена локально или на серве
// Пока сделал локально, и возвращаться тогда будет только пока UserAuthDto с UserDto

class AuthRepository(
    val authNetworkDataSource: AuthNetworkDataSource,
    val authLocalDataSource: AuthLocalDataSource,
): IAuthRepository {

    override suspend fun checkAndAuth(
        login: String,
        password: String
    ): Result<UserAuth> {

        val tempToken = genBasicToken(login,password)
        authLocalDataSource.setToken(tempToken)

        val result = authNetworkDataSource.tryAuthByToken().fold(
            onSuccess = { userAuthDto ->
                val userAuth = userAuthDto.toDomain()

                if (userAuth != null) Result.success(userAuth)
                else Result.failure(Exception("UserAuth is null"))
            },
            onFailure = { // incorrect credentials|token
                authLocalDataSource.clearToken()
                Result.failure(it)
            }
        )
        return result
    }

    // "remember me"
    override suspend fun tryAuthByToken(): Result<UserAuth> {
        return authNetworkDataSource.tryAuthByToken().mapCatching { userAuthDto ->
            userAuthDto.toDomain() ?: throw Exception("Data is corrupted")
            // mapCatching превратит exception в failure
        }
    }

    fun genBasicToken(login: String, password: String): String {
        val phrase = "$login:$password"
        return "Basic " + Base64.encode(phrase.toByteArray())
    }
}