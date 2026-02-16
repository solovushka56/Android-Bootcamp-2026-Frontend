package ru.sicampus.bootcamp2026.domain.entities.invite

import java.time.Instant

data class Invite(
    val id: Long,
    val meetId: Int,
    val inviterUserId: Int,
    val invitedUserId: Int,
    val status: InviteStatus,
    val createdAt: Instant
)