package ru.sicampus.bootcamp2026.domain.usecases.meets

import ru.sicampus.bootcamp2026.data.repos.MeetsRepository
import ru.sicampus.bootcamp2026.domain.contracts.IMeetsRepository
import ru.sicampus.bootcamp2026.domain.entities.meet.Meet

class GetActiveMeetsUseCase(
    private val meetsRepository: IMeetsRepository
) {
    suspend operator fun invoke(): Result<List<Meet>> {
        return meetsRepository.getMeets()
    }
}