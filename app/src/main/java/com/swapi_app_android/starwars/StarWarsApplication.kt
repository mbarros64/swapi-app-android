package com.swapi_app_android.starwars

import android.app.Application
import com.swapi_app_android.starwars.dependencies.BaseDependencies
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StartWarsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StartWarsApplication)
            modules(BaseDependencies.networkingModule)
        }
    }
}