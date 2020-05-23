package com.example.shared.common.module

import android.content.Context

interface ShareModuleFacade {
    fun getContext(): Context
}

class EmptyShareModule : ShareModuleFacade {
    override fun getContext(): Context = throw throwValidation()

    private fun throwValidation(): Throwable =
        IllegalStateException("Data module is not built. Please call build() and wait the initialisation finishes.")
}

class DefaultShareModule(private val shareModuleBuilder: ShareModuleBuilder) : ShareModuleFacade {
    override fun getContext(): Context = shareModuleBuilder.context
}