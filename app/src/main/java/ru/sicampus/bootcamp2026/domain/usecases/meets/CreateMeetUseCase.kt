package ru.sicampus.bootcamp2026.domain.usecases.meets

import ru.sicampus.bootcamp2026.domain.contracts.IMeetsRepository
import ru.sicampus.bootcamp2026.domain.entities.meet.Meet

class CreateMeetUseCase(
    val meetRepository: IMeetsRepository
) {
    suspend operator fun invoke(meet: Meet): Result<Unit> =
        meetRepository.createMeet(meet)
}