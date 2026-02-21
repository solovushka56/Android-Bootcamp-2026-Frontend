package ru.sicampus.bootcamp2026.data.source.local

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.sicampus.bootcamp2026.data.dto.UserDto
import ru.sicampus.bootcamp2026.domain.entities.user.User

class ProfileLocalDataSource {
    private val _currentUser = MutableStateFlow<UserDto?>(null)
    val currentUser = _currentUser.asStateFlow()

    suspend fun setUser(dto: UserDto) {
        _currentUser.value = dto
    }

    suspend fun clearUser() {
        _currentUser.value = null
    }

}