package ru.sicampus.bootcamp2026.data.repos

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.sicampus.bootcamp2026.data.mappers.toDomain
import ru.sicampus.bootcamp2026.data.mappers.toDto
import ru.sicampus.bootcamp2026.data.source.local.ProfileLocalDataSource
import ru.sicampus.bootcamp2026.data.source.remote.ProfileNetworkDataSource
import ru.sicampus.bootcamp2026.domain.contracts.IProfileRepository
import ru.sicampus.bootcamp2026.domain.entities.User

class ProfileRepository(
    val profileLocalDataSource: ProfileLocalDataSource,
    val profileNetworkDataSource: ProfileNetworkDataSource,
): IProfileRepository {


    override suspend fun editProfile(editedUser: User): Result<Unit> {
        val result = profileNetworkDataSource.editProfile(editedUser.toDto())
        if (result.isSuccess) profileLocalDataSource.setUser(editedUser.toDto())
        return result
    }

    override suspend fun getCurrentProfile(): Flow<User?> {
        return profileLocalDataSource.currentUser.map { it?.toDomain() }
    }
}