package com.e.wedding.app.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.e.wedding.app.App
import com.e.wedding.app.di.ActivityComponent
import com.e.wedding.app.di.ActivityCompositionRoot

abstract class BaseActivity : AppCompatActivity, Tagged {

    constructor() : super()

    constructor(@LayoutRes fragmentLayoutRes: Int) : super(fragmentLayoutRes)

    private val _compositionRoot by lazy { ActivityCompositionRoot((application as App).appCompositionRoot, this) }
    val compositionRoot: ActivityComponent get() = _compositionRoot
}

inline fun <reified T : ViewModel> BaseActivity.viewModel(): Lazy<T> = lazy { compositionRoot.viewModelProvider.get(T::class.java) }