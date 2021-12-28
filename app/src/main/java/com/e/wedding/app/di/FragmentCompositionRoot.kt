package com.e.wedding.app.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.ViewModelFactory

/**
 * Place for all dependencies scoped to a fragment's lifecycle
 */
class FragmentCompositionRoot(
    activityComponent: ActivityComponent,
    fragment: BaseFragment
) : ActivityComponent by activityComponent {

    override val viewModelStoreOwner: ViewModelStoreOwner = fragment

    override val viewModelProvider by lazy { ViewModelProvider(fragment, ViewModelFactory(this, fragment, fragment.arguments)) }

    val activityViewModelProvider by lazy { ViewModelProvider(activity.viewModelStore, ViewModelFactory(this, activity, activity.intent?.extras)) }
}