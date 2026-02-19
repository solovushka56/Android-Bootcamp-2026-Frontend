package ru.sicampus.bootcamp2026.domain.usecases.auth

import ru.sicampus.bootcamp2026.domain.contracts.IAuthRepository
import ru.sicampus.bootcamp2026.domain.entities.auth.UserAuth

class AuthByTokenUseCase(
    val authRepository: IAuthRepository,
) {
    suspend operator fun invoke(): Result<UserAuth> {
        return authRepository.tryAuthByToken()
    }
}