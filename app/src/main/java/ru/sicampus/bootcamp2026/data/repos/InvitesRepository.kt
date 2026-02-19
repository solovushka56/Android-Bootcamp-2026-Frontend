package ru.sicampus.bootcamp2026.data.repos

import ru.sicampus.bootcamp2026.data.mappers.toDomain
import ru.sicampus.bootcamp2026.data.source.remote.InvitesDataSource
import ru.sicampus.bootcamp2026.domain.contracts.IInvitesRepository
import ru.sicampus.bootcamp2026.domain.entities.invite.Invite

class InvitesRepository(
    private val invitesDataSource: InvitesDataSource
): IInvitesRepository {

    override suspend fun getInvites(): Result<List<Invite>> {
        return invitesDataSource.genInvites().map { inviteDtos ->
            inviteDtos.mapNotNull { inviteDto -> inviteDto.toDomain() }
        }
    }

    override suspend fun createInvite(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun removeInvite(invite: Invite): Result<Unit> {
        TODO("Not yet implemented")
    }
}