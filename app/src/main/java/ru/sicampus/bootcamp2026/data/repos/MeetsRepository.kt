package ru.sicampus.bootcamp2026.data.repos

import ru.sicampus.bootcamp2026.data.mappers.toDomain
import ru.sicampus.bootcamp2026.data.source.remote.MeetsDataSource
import ru.sicampus.bootcamp2026.domain.contracts.IMeetsRepository
import ru.sicampus.bootcamp2026.domain.entities.meet.Meet

class MeetsRepository(
    private val meetsDataSource: MeetsDataSource
): IMeetsRepository {
    override suspend fun getMeets(): Result<List<Meet>> {
        return meetsDataSource.getMeets().map { meetDtos ->
            meetDtos.mapNotNull { meetDto -> meetDto.toDomain() }
        }
    }

    override suspend fun createMeet(meet: Meet): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun removeMeet(id: Meet): Result<Unit> {
        TODO("Not yet implemented")
    }


}