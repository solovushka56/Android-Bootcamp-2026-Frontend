package ru.sicampus.bootcamp2026.domain.usecases.users

import ru.sicampus.bootcamp2026.data.repos.UsersRepository
import ru.sicampus.bootcamp2026.domain.entities.User

class SearchUsersUseCase(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke(searchQuery: String): Result<List<User>> {
        return userRepository.findUsers(searchQuery)
    }
}