package ru.sicampus.bootcamp2026.app

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.sicampus.bootcamp2026.app.di.appModules
import ru.sicampus.bootcamp2026.app.di.fakeAppModules

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(fakeAppModules)
        }
    }
}