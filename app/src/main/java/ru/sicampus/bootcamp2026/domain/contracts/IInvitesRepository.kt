package ru.sicampus.bootcamp2026.domain.contracts

import ru.sicampus.bootcamp2026.domain.entities.invite.Invite

interface IInvitesRepository {
    suspend fun getInvites(): Result<List<Invite>>
}