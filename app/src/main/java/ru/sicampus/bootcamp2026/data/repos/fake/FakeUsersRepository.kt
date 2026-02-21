package ru.sicampus.bootcamp2026.data.repos.fake

import ru.sicampus.bootcamp2026.domain.contracts.IUsersRepository
import ru.sicampus.bootcamp2026.domain.entities.user.User

class FakeUsersRepository: IUsersRepository {
    override suspend fun findUsers(query: String): Result<List<User>> {
        TODO("Not yet implemented")
    }
}