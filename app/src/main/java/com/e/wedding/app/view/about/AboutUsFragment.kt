package com.e.wedding.app.view.about

import android.os.Bundle
import android.view.View
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.app.model.DataHolder
import com.e.wedding.databinding.FragmentAboutUsBinding

class AboutUsFragment : BaseFragment(R.layout.fragment_about_us) {
    private val binding by viewBinding(FragmentAboutUsBinding::bind)
    private val viewModel by viewModel<AboutUsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        textAboutUs.text = DataHolder.getGuestLoggedIn()?.convitetext1 ?: "User ainda nao fez login"
    }
}