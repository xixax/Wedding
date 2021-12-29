package com.e.wedding.app.view.home

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        Glide.with(this)
            .load("https://drive.google.com/u/0/uc?id=1hpDekq5YsE4isI5XXE7RqpXvM6zCoN6R&export=download")
            .into(binding.homeBackground)
    }
}