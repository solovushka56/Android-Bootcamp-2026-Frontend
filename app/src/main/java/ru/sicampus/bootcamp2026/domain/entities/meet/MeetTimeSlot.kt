package ru.sicampus.bootcamp2026.domain.entities.meet

import java.time.LocalDate

data class MeetTimeSlot(
    val id: Long = 0,
    val date: LocalDate,
    val startHour: Int,
    val endHour: Int
)
