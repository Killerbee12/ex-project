package com.example.shared.di

import com.example.shared.network.createNetworkClient
import org.koin.dsl.module.module

val shredModule = module {
    single(name = "Retrofit") { createNetworkClient() }
}