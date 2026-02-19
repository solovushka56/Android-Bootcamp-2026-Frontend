package ru.sicampus.bootcamp2026.domain.usecases.profile

import ru.sicampus.bootcamp2026.data.repos.ProfileRepository
import ru.sicampus.bootcamp2026.domain.contracts.IProfileRepository

class GetMyProfileUseCase(
    val profileRepository: IProfileRepository
) {
    suspend operator fun invoke() = profileRepository.getCurrentProfile()
}