package com.example.project.di

import com.example.project.NavigationProviderImpl
import com.example.shared.navigation.NavigationProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single<NavigationProvider> {
        NavigationProviderImpl(androidContext())
    }
}