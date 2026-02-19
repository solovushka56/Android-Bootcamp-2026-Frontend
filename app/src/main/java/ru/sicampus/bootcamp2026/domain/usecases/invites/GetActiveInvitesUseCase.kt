package ru.sicampus.bootcamp2026.domain.usecases.invites

import ru.sicampus.bootcamp2026.domain.contracts.IInvitesRepository
import ru.sicampus.bootcamp2026.domain.entities.invite.Invite

class GetActiveInvitesUseCase(
    val invitesRepository: IInvitesRepository
) {
    suspend operator fun invoke(): Result<List<Invite>> {
        return invitesRepository.getInvites()
    }
}