package ru.sicampus.bootcamp2026.domain.usecases.auth

import ru.sicampus.bootcamp2026.domain.contracts.IAuthRepository

class LogoutUseCase(
    val authRepository: IAuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.logout() // return success or not
    }
}