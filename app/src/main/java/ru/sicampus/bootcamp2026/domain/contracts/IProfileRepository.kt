package ru.sicampus.bootcamp2026.domain.contracts

import kotlinx.coroutines.flow.Flow
import ru.sicampus.bootcamp2026.domain.entities.user.User

interface IProfileRepository {
    suspend fun editProfile(editedUser: User): Result<Unit>

    suspend fun getCurrentProfile(): Flow<User?>
}