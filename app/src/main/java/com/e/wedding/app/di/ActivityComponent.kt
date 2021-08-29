package com.e.wedding.app.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.e.wedding.app.base.BaseActivity

interface ActivityComponent : AppComponent {

    val activity: BaseActivity

    val viewModelStoreOwner: ViewModelStoreOwner

    val viewModelProvider: ViewModelProvider
}