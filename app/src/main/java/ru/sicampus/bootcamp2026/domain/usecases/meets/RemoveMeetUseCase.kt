package ru.sicampus.bootcamp2026.domain.usecases.meets

import ru.sicampus.bootcamp2026.domain.contracts.IMeetsRepository
import ru.sicampus.bootcamp2026.domain.entities.meet.Meet

class RemoveMeetUseCase(
    val meetsRemoveMeetUseCase: IMeetsRepository
) {
    suspend operator fun invoke(meet: Meet) = meetsRemoveMeetUseCase.removeMeet(meet)
}