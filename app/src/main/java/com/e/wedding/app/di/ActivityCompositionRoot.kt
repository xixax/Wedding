package com.e.wedding.app.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import com.e.wedding.R
import com.e.wedding.app.base.BaseActivity
import com.e.wedding.app.base.ViewModelFactory

/**
 * Place for all dependencies scoped to an activity's lifecycle.
 */
class ActivityCompositionRoot(
    appComponent: AppComponent,
    override val activity: BaseActivity
) : ActivityComponent, AppComponent by appComponent {

    override val viewModelProvider by lazy { ViewModelProvider(viewModelStoreOwner, ViewModelFactory(this, activity)) }

    override val viewModelStoreOwner: ViewModelStoreOwner = activity

    override val navController by lazy { activity.findNavController(R.id.nav_host_fragment) }
}
