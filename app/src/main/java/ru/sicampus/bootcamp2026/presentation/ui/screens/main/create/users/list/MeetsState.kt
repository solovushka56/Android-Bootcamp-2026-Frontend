package ru.sicampus.bootcamp2026.presentation.ui.screens.main.create.users.list

import ru.sicampus.bootcamp2026.domain.entities.meet.Meet

sealed interface MeetsState {
    data object Loading: MeetsState
    data class Error(
        val reason: String
    ): MeetsState
    data class Content(
        val meets: List<Meet>
    ) : MeetsState

}