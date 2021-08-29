package com.e.wedding.app.view.breakfast

import android.os.Bundle
import android.view.View
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.app.model.DataHolder
import com.e.wedding.databinding.FragmentBreakfastBinding

class BreakfastFragment : BaseFragment(R.layout.fragment_breakfast) {
    private val binding by viewBinding(FragmentBreakfastBinding::bind)
    private val viewModel by viewModel<BreakfastViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        textBreakfast.text = DataHolder.getGuestLoggedIn()?.convitetext1 ?: "User ainda nao fez login"
    }
}