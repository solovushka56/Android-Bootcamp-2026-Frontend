package ru.sicampus.bootcamp2026.presentation.ui.screens.main.invites

import ru.sicampus.bootcamp2026.domain.entities.invite.Invite

sealed interface InvitesState {
    data object Loading: InvitesState
    data class Error(
        val reason: String
    ): InvitesState
    data class Content(
        val users: List<Invite>
    ) : InvitesState
}