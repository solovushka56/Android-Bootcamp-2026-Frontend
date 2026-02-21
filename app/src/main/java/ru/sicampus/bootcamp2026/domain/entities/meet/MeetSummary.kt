package ru.sicampus.bootcamp2026.domain.entities.meet

import ru.sicampus.bootcamp2026.domain.entities.user.UserShort

data class MeetSummary(
    val id: Long,
    val title: String,
    val timeSlot: MeetTimeSlot,
    val organizer: UserShort,
    val membersCount: Int,
    val invitedCount: Int
)

