package com.e.wedding.app.view.gallery

import android.os.Bundle
import android.view.View
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.app.model.DataHolder
import com.e.wedding.app.view.gift.GiftViewModel
import com.e.wedding.databinding.FragmentGalleryBinding

class GalleryFragment : BaseFragment(R.layout.fragment_gallery) {
    private val binding by viewBinding(FragmentGalleryBinding::bind)
    private val viewModel by viewModel<GiftViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        textGallery.text =
            DataHolder.getGuestLoggedIn()?.convitetext1 ?: "User ainda nao fez login"
    }
}