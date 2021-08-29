package com.e.wedding.app

import android.app.Application
import com.e.wedding.app.di.AppComponent
import com.e.wedding.app.di.AppCompositionRoot

class App : Application() {

    private val _appCompositionRoot = AppCompositionRoot(this)
    val appCompositionRoot: AppComponent get() = _appCompositionRoot

}