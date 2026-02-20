package ru.sicampus.bootcamp2026.app.di

import org.koin.dsl.module
import ru.sicampus.bootcamp2026.domain.usecases.auth.AuthByCredentialsUseCase
import ru.sicampus.bootcamp2026.domain.usecases.auth.AuthByTokenUseCase
import ru.sicampus.bootcamp2026.domain.usecases.auth.LogoutUseCase
import ru.sicampus.bootcamp2026.domain.usecases.invites.GetActiveInvitesUseCase
import ru.sicampus.bootcamp2026.domain.usecases.invites.InviteUsersToMeetUseCase
import ru.sicampus.bootcamp2026.domain.usecases.invites.RespondToInviteUserCase
import ru.sicampus.bootcamp2026.domain.usecases.meets.CreateMeetUseCase
import ru.sicampus.bootcamp2026.domain.usecases.meets.GetActiveMeetsUseCase
import ru.sicampus.bootcamp2026.domain.usecases.meets.RemoveMeetUseCase
import ru.sicampus.bootcamp2026.domain.usecases.profile.GetMyProfileUseCase
import ru.sicampus.bootcamp2026.domain.usecases.profile.UpdateMyProfileUseCase
import ru.sicampus.bootcamp2026.domain.usecases.users.SearchUsersUseCase

val useCaseModule = module {
    factory { AuthByCredentialsUseCase(get()) }
    factory { AuthByTokenUseCase(get()) }
    factory { LogoutUseCase(get()) }

    factory { GetActiveInvitesUseCase(get()) }
    factory { InviteUsersToMeetUseCase() }
    factory { RespondToInviteUserCase() }

    factory { CreateMeetUseCase(get()) }
    factory { GetActiveMeetsUseCase(get()) }
    factory { RemoveMeetUseCase(get()) }

    factory { GetMyProfileUseCase(get()) }
    factory { UpdateMyProfileUseCase() }

    factory { SearchUsersUseCase(get()) }
}