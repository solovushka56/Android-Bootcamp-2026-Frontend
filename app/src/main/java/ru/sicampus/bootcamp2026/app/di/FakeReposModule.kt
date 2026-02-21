package ru.sicampus.bootcamp2026.app.di

import org.koin.dsl.module
import ru.sicampus.bootcamp2026.data.repos.AuthRepository
import ru.sicampus.bootcamp2026.data.repos.InvitesRepository
import ru.sicampus.bootcamp2026.data.repos.MeetsRepository
import ru.sicampus.bootcamp2026.data.repos.ProfileRepository
import ru.sicampus.bootcamp2026.data.repos.UsersRepository
import ru.sicampus.bootcamp2026.data.repos.fake.FakeAuthRepository
import ru.sicampus.bootcamp2026.data.repos.fake.FakeInvitesRepository
import ru.sicampus.bootcamp2026.data.repos.fake.FakeMeetsRepository
import ru.sicampus.bootcamp2026.data.repos.fake.FakeProfileRepository
import ru.sicampus.bootcamp2026.data.repos.fake.FakeUsersRepository
import ru.sicampus.bootcamp2026.domain.contracts.IAuthRepository
import ru.sicampus.bootcamp2026.domain.contracts.IInvitesRepository
import ru.sicampus.bootcamp2026.domain.contracts.IMeetsRepository
import ru.sicampus.bootcamp2026.domain.contracts.IProfileRepository
import ru.sicampus.bootcamp2026.domain.contracts.IUsersRepository

val fakeReposModule = module {
    single<IAuthRepository> { FakeAuthRepository() }
    single<IInvitesRepository> { FakeInvitesRepository() }
    single<IMeetsRepository> { FakeMeetsRepository() }
    single<IProfileRepository> { FakeProfileRepository() }
    single<IUsersRepository> { FakeUsersRepository() }
}