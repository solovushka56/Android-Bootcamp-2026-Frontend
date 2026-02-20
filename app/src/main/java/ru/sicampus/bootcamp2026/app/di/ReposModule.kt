package ru.sicampus.bootcamp2026.app.di

import org.koin.dsl.module
import ru.sicampus.bootcamp2026.data.repos.AuthRepository
import ru.sicampus.bootcamp2026.data.repos.InvitesRepository
import ru.sicampus.bootcamp2026.data.repos.MeetsRepository
import ru.sicampus.bootcamp2026.data.repos.ProfileRepository
import ru.sicampus.bootcamp2026.data.repos.UsersRepository
import ru.sicampus.bootcamp2026.domain.contracts.IAuthRepository
import ru.sicampus.bootcamp2026.domain.contracts.IInvitesRepository
import ru.sicampus.bootcamp2026.domain.contracts.IMeetsRepository
import ru.sicampus.bootcamp2026.domain.contracts.IProfileRepository
import ru.sicampus.bootcamp2026.domain.contracts.IUsersRepository

val reposModule = module {
    single<IAuthRepository> { AuthRepository(get(), get(), get()) }
    single<IInvitesRepository> { InvitesRepository(get()) }
    single<IMeetsRepository> { MeetsRepository(get()) }
    single<IProfileRepository> { ProfileRepository(get(), get()) }
    single<IUsersRepository> { UsersRepository(get()) }

}