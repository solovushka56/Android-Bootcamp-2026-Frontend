package ru.sicampus.bootcamp2026.data.repos.fake

import kotlinx.coroutines.flow.Flow
import ru.sicampus.bootcamp2026.domain.contracts.IProfileRepository
import ru.sicampus.bootcamp2026.domain.entities.user.User

class FakeProfileRepository: IProfileRepository {
    override suspend fun editProfile(editedUser: User): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentProfile(): Flow<User?> {
        TODO("Not yet implemented")
    }
}