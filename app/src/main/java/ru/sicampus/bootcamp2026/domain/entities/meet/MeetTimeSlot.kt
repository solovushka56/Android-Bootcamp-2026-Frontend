package ru.sicampus.bootcamp2026.domain.entities.meet

import java.time.LocalDate

data class MeetTimeSlot(
    val id: Long,
    val date: LocalDate,
    val startHour: Int,
    val endHour: Int
)
