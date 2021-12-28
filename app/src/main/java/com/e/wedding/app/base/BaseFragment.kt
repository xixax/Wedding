package com.e.wedding.app.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.e.wedding.app.di.FragmentCompositionRoot

abstract class BaseFragment : Fragment, Tagged {

    constructor() : super()

    constructor(@LayoutRes fragmentLayoutRes: Int) : super(fragmentLayoutRes)

    val compositionRoot by lazy {
        FragmentCompositionRoot((requireActivity() as BaseActivity).compositionRoot, this)
    }
}

inline fun <reified T : ViewModel> BaseFragment.viewModel(): Lazy<T> = lazy { compositionRoot.viewModelProvider.get(T::class.java) }
inline fun <reified T : ViewModel> BaseFragment.activityViewModel(): Lazy<T> = lazy { compositionRoot.activityViewModelProvider.get(T::class.java) }