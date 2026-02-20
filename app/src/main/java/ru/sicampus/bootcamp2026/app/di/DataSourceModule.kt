package ru.sicampus.bootcamp2026.app.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.sicampus.bootcamp2026.data.source.local.AuthLocalDataSource
import ru.sicampus.bootcamp2026.data.source.local.ProfileLocalDataSource
import ru.sicampus.bootcamp2026.data.source.remote.AuthNetworkDataSource
import ru.sicampus.bootcamp2026.data.source.remote.InvitesDataSource
import ru.sicampus.bootcamp2026.data.source.remote.MeetsDataSource
import ru.sicampus.bootcamp2026.data.source.remote.ProfileNetworkDataSource
import ru.sicampus.bootcamp2026.data.source.remote.UsersDataSource

val dataSourceModule = module {
    single { AuthLocalDataSource(androidContext()) } // можно даже get() т.к. принимаем context
    single { AuthNetworkDataSource(get()) }

    single { InvitesDataSource(get()) }
    single { MeetsDataSource(get()) }

    single { ProfileLocalDataSource() }
    single { ProfileNetworkDataSource(get()) }

    single { UsersDataSource(get()) }
}