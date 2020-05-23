package com.example.project

import android.app.Application
import com.example.excoroutines.di.exCoroutinesModule
import com.example.project.di.appModule
import com.example.shared.di.shredModule
import org.koin.android.ext.android.startKoin

class ExampleProjectApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(
            this,
            listOf(
                shredModule,
                appModule,
                exCoroutinesModule
            )
        )
    }
}