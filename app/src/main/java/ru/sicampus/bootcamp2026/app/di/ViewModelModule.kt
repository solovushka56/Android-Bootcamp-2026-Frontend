package ru.sicampus.bootcamp2026.app.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.sicampus.bootcamp2026.presentation.ui.screens.main.create.users.list.UsersViewModel
import ru.sicampus.bootcamp2026.presentation.ui.screens.main.invites.InvitesViewModel
import ru.sicampus.bootcamp2026.presentation.ui.screens.main.meets.MeetsViewModel
import ru.sicampus.bootcamp2026.presentation.ui.screens.main.profile.ProfileViewModel
import ru.sicampus.bootcamp2026.presentation.ui.screens.start.login.LoginViewModel
import ru.sicampus.bootcamp2026.presentation.ui.screens.start.reg.RegViewModel

val viewModelModule = module {
    viewModel { LoginViewModel() }
    viewModel { RegViewModel() }

    viewModel { InvitesViewModel() }
    viewModel { UsersViewModel() }
    viewModel { MeetsViewModel(get(), get()) }

    viewModel { ProfileViewModel() }
}