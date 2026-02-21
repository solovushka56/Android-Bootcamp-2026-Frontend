package ru.sicampus.bootcamp2026.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MeetDto(
    @SerialName("id") val id: Long?,
    @SerialName("organizerId") val organizerId: Int?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("timeSlot") val timeSlot: MeetTimeSlotDto?,
    @SerialName("membersIds") val membersIds: List<Long>?,
    @SerialName("invitedIds") val invitedIds: List<Long>?,
    @SerialName("createdAt") val createdAt: String?, // ISO-8601 todo
)

@Serializable
data class MeetTimeSlotDto(
    @SerialName("id") val id: Long?,
    @SerialName("date") val date: String?,
    @SerialName("startHour") val startHour: Int?,
    @SerialName("endHour") val endHour: Int?
)