package ru.sicampus.bootcamp2026.domain.usecases.auth

import ru.sicampus.bootcamp2026.domain.contracts.IAuthRepository
import ru.sicampus.bootcamp2026.domain.entities.auth.UserAuth

class AuthByCredentialsUseCase(
    val authRepository: IAuthRepository
) {
    suspend operator fun invoke(login: String, password: String): Result<UserAuth> {
        return authRepository.checkAndAuth(login, password)
    }
}