package com.e.wedding.app.base

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.e.wedding.app.di.ActivityComponent
import com.e.wedding.app.view.about.AboutUsViewModel
import com.e.wedding.app.view.breakfast.BreakfastViewModel
import com.e.wedding.app.view.ceremony.CeremonyViewModel
import com.e.wedding.app.view.engagement.EngagementViewModel
import com.e.wedding.app.view.gallery.GalleryViewModel
import com.e.wedding.app.view.gift.GiftViewModel
import com.e.wedding.app.view.home.HomeViewModel
import com.e.wedding.app.view.invite.InviteViewModel
import com.e.wedding.app.view.menu.MenuViewModel

class ViewModelFactory(
    private val activityComponent: ActivityComponent,
    savedStateRegistryOwner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, defaultArgs), Tagged {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle) = when (modelClass) {
        MenuViewModel::class.java -> MenuViewModel()
        InviteViewModel::class.java -> InviteViewModel()
        HomeViewModel::class.java -> HomeViewModel()
        AboutUsViewModel::class.java -> AboutUsViewModel()
        BreakfastViewModel::class.java -> BreakfastViewModel()
        CeremonyViewModel::class.java -> CeremonyViewModel()
        EngagementViewModel::class.java -> EngagementViewModel()
        GiftViewModel::class.java -> GiftViewModel()
        GalleryViewModel::class.java -> GalleryViewModel()
        else -> throw RuntimeException("Oops! Did you forget to add $modelClass to ViewModelFactory?")
    } as T
}