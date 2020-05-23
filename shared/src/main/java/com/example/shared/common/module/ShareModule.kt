package com.example.shared.common.module

import android.content.Context

class ShareModule private constructor() {
    companion object {

        @Volatile
        private var shareModuleFacade: ShareModuleFacade = EmptyShareModule()

        @Synchronized
        fun init(context: Context): ShareModuleBuilder {
            requireNotNull(context) { "Non-null context required." }
            return ShareModuleBuilder(context)
        }

        fun build(shareModuleBuilder: ShareModuleBuilder) {
            shareModuleFacade = DefaultShareModule(shareModuleBuilder)
        }

        fun getContext(): Context = shareModuleFacade.getContext()
    }
}