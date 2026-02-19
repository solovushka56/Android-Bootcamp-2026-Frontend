package ru.sicampus.bootcamp2026.data.repos

import ru.sicampus.bootcamp2026.data.mappers.toDomain
import ru.sicampus.bootcamp2026.data.mappers.toDto
import ru.sicampus.bootcamp2026.data.source.local.AuthLocalDataSource
import ru.sicampus.bootcamp2026.data.source.local.ProfileLocalDataSource
import ru.sicampus.bootcamp2026.data.source.remote.AuthNetworkDataSource
import ru.sicampus.bootcamp2026.data.source.remote.ProfileNetworkDataSource
import ru.sicampus.bootcamp2026.domain.contracts.IAuthRepository
import ru.sicampus.bootcamp2026.domain.entities.auth.UserAuth
import kotlin.io.encoding.Base64

// Сейчас нужно определиться, какой у нас тип авторизации
// Т.е. какой токен. Так мы поймём, делать ген токена локально или на серве
// Пока сделал локально, и возвращаться тогда будет только пока UserAuthDto с UserDto

class AuthRepository(
    val authNetworkDataSource: AuthNetworkDataSource,
    val authLocalDataSource: AuthLocalDataSource,
    val profileLocalDataSource: ProfileLocalDataSource
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
                if (userAuth != null) {
                    profileLocalDataSource.setUser(userAuthDto.userDto!!)
                    Result.success(userAuth)
                }
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
            val user = userAuthDto.toDomain() ?: throw Exception("Data is corrupted")
            profileLocalDataSource.setUser(userAuthDto.userDto!!)
            return@mapCatching user
        }
    }

    override suspend fun logout(): Result<Unit> {
        authLocalDataSource.clearAllData()
        profileLocalDataSource.clearUser()
        // todo add server request about logout
        return Result.success(Unit)
    }


    fun genBasicToken(login: String, password: String): String {
        val phrase = "$login:$password"
        return "Basic " + Base64.encode(phrase.toByteArray())
    }
}