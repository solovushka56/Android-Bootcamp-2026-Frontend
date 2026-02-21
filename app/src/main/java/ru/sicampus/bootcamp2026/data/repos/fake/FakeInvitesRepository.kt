package ru.sicampus.bootcamp2026.data.repos.fake

import ru.sicampus.bootcamp2026.domain.contracts.IInvitesRepository
import ru.sicampus.bootcamp2026.domain.entities.invite.Invite

class FakeInvitesRepository: IInvitesRepository {
    override suspend fun getInvites(): Result<List<Invite>> {
        TODO("Not yet implemented")
    }

    override suspend fun inviteUser(
        meetId: Long,
        invitedUserId: Long
    ): Result<Unit> {
        TODO("Not yet implemented")
    }
}