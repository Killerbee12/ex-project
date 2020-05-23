package com.example.shared.navigation

import com.example.shared.navigation.module.ExCorountinesModule

interface NavigationProvider {
    fun toMainActivity()
    fun providerExCorountineNavigation(): ExCorountinesModule
}