package ru.sicampus.bootcamp2026.domain.contracts

import ru.sicampus.bootcamp2026.domain.entities.meet.Meet

interface IMeetsRepository {
    suspend fun getMeets(): Result<List<Meet>>
    suspend fun createMeet(meet: Meet): Result<Unit>
    suspend fun removeMeet(id: Meet): Result<Unit> // when ended for example


}