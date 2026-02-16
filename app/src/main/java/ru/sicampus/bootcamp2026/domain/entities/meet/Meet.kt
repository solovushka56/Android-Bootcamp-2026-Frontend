package ru.sicampus.bootcamp2026.domain.entities.meet
import java.time.Instant

data class Meet(
    val id: Long,
    val organizerId: Int,
    val title: String,
    val description: String?,
    val timeSlot: MeetTimeSlot,
    val membersIds: Set<Int>,
    val invitedIds: Set<Int>,
    val createdAt: Instant
)
