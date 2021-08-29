package com.e.wedding.app.di

import com.e.wedding.app.App
import com.e.wedding.app.base.AndroidLogger
import com.e.wedding.app.base.Logger

class AppCompositionRoot(override val app: App) : AppComponent {

    init {
        Logger.injectLogger(AndroidLogger())
    }
}