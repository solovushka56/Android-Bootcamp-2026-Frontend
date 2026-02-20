package ru.sicampus.bootcamp2026.data.repos

import ru.sicampus.bootcamp2026.data.mappers.toDomain
import ru.sicampus.bootcamp2026.data.source.remote.UsersDataSource
import ru.sicampus.bootcamp2026.domain.contracts.IUsersRepository
import ru.sicampus.bootcamp2026.domain.entities.User

class UsersRepository(
    private val usersDataSource: UsersDataSource
): IUsersRepository {

    // returns failure if user not exists or cant map to domain then we display "user not found"
    override suspend fun findUsers(query: String): Result<List<User>> {
        return usersDataSource.getUsersBySearch(query).map { userDtos ->
            userDtos.mapNotNull { userDto -> userDto.toDomain() }
        }
    }

//    override suspend fun findUsersByPosition(position: String): Result<List<User>> {
//        TODO("Not yet implemented")
//    }
}
