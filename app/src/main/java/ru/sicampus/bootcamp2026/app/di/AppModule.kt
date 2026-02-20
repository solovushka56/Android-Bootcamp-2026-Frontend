package ru.sicampus.bootcamp2026.app.di

import org.koin.dsl.module

val appModules = listOf(
    dataSourceModule,
    reposModule,
    useCaseModule,
    viewModelModule,
)