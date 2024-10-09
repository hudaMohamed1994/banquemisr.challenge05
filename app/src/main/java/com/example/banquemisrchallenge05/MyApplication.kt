package com.example.banquemisrchallenge05

import android.app.Application
import com.example.banquemisrchallenge05.koin.appModule
import com.example.data.koin.dataModule
import com.example.domain.koin.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/** Implement modular architecture for better separation of concerns and code maintainability
Integrate Koin for dependency injection to streamline object creation and management
Add OkHttp interceptor to manage API key securely for all network requests **/


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Load modules using Koin
            androidContext(this@MyApplication)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}