package ru.sicampus.bootcamp2026.data.repos.fake

import ru.sicampus.bootcamp2026.domain.contracts.IMeetsRepository
import ru.sicampus.bootcamp2026.domain.entities.meet.Meet
import ru.sicampus.bootcamp2026.domain.entities.meet.MeetTimeSlot
import ru.sicampus.bootcamp2026.domain.entities.user.UserShort
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

class FakeMeetsRepository: IMeetsRepository {
    val meets = mutableListOf<Meet>(
        Meet(
            id = 1,
            organizerId = 101,
            title = "Sprint Planning",
            description = "Планирование задач на следующий спринт",
            timeSlot = MeetTimeSlot(
                id = 1,
                date = LocalDate.of(2026, 2, 21),
                startHour = 10,
                endHour = 12
            ),
            membersIds = listOf(101, 102, 103),
            invitedIds = listOf(104, 105),
            createdAt = Instant.now()
        ),
        Meet(
            id = 2,
            organizerId = 102,
            title = "Design Review",
            description = "Обсуждение нового UI",
            timeSlot = MeetTimeSlot(
                id = 2,
                date = LocalDate.of(2026, 2, 22),
                startHour = 14,
                endHour = 16
            ),
            membersIds = listOf(102, 106),
            invitedIds = listOf(101, 107),
            createdAt = Instant.now()
        ),
//        Meet(
//            id = 3,
//            organizerId = 103,
//            title = "Backend Sync",
//            description = null,
//            timeSlot = MeetTimeSlot(
//                id = 3,
//                date = LocalDate.of(2026, 2, 23),
//                startHour = 11,
//                endHour = 13
//            ),
//            membersIds = listOf(103, 108),
//            invitedIds = listOf(109),
//            createdAt = Instant.now()
//        ),
//        Meet(
//            id = 4,
//            organizerId = 104,
//            title = "1:1 Meeting",
//            description = "Индивидуальная встреча",
//            timeSlot = MeetTimeSlot(
//                id = 4,
//                date = LocalDate.of(2026, 2, 24),
//                startHour = 9,
//                endHour = 10
//            ),
//            membersIds = listOf(104, 101),
//            invitedIds = emptyList(),
//            createdAt = Instant.now()
//        ),
//        Meet(
//            id = 5,
//            organizerId = 105,
//            title = "Architecture Discussion",
//            description = "Обсуждение микросервисной архитектуры",
//            timeSlot = MeetTimeSlot(
//                id = 5,
//                date = LocalDate.of(2026, 2, 25),
//                startHour = 15,
//                endHour = 18
//            ),
//            membersIds = listOf(105, 102, 103),
//            invitedIds = listOf(110, 111),
//            createdAt = Instant.now()
//        ),
//        Meet(
//            id = 6,
//            organizerId = 106,
//            title = "Retrospective",
//            description = "Итоги прошедшего спринта",
//            timeSlot = MeetTimeSlot(
//                id = 6,
//                date = LocalDate.of(2026, 2, 26),
//                startHour = 13,
//                endHour = 14
//            ),
//            membersIds = listOf(106, 101, 104),
//            invitedIds = listOf(102),
//            createdAt = Instant.now()
//        ),
//        Meet(
//            id = 7,
//            organizerId = 107,
//            title = "Product Demo",
//            description = "Демонстрация функционала заказчику",
//            timeSlot = MeetTimeSlot(
//                id = 7,
//                date = LocalDate.of(2026, 2, 27),
//                startHour = 16,
//                endHour = 17
//            ),
//            membersIds = listOf(107, 103),
//            invitedIds = listOf(101, 105),
//            createdAt = Instant.now()
//        )
    )


    override suspend fun getMeets(): Result<List<Meet>> {
        return Result.success(meets)
    }

    override suspend fun createMeet(meet: Meet): Result<Unit> {
        val success = meets.add(meet)
        if (success) return Result.success(Unit)
        // todo validate meet usecase???
        else return Result.failure(Exception("Cant add meet"))
    }

    override suspend fun removeMeet(id: Meet): Result<Unit> {
        TODO("Not yet implemented")
    }
}