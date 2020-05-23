package com.example.project

import android.content.Context
import android.content.Intent
import com.example.excoroutines.presentation.ExCoroutinesMainActivity
import com.example.shared.navigation.NavigationProvider
import com.example.shared.navigation.module.ExCorountinesModule

class NavigationProviderImpl constructor(private val context: Context) : NavigationProvider {

    override fun toMainActivity() {
        context.startActivity(
            Intent(context, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }

    override fun providerExCorountineNavigation() = object : ExCorountinesModule {
        override fun toExCorountineMainActivity() {
            context.startActivity(
                Intent(context, ExCoroutinesMainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }
}