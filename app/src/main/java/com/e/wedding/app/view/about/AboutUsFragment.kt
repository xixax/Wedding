package com.e.wedding.app.view.about

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.app.model.DataHolder
import com.e.wedding.app.model.Image
import com.e.wedding.app.view.gift.GiftAdapter
import com.e.wedding.databinding.FragmentAboutUsBinding

class AboutUsFragment : BaseFragment(R.layout.fragment_about_us) {
    private val binding by viewBinding(FragmentAboutUsBinding::bind)
    private val viewModel by viewModel<AboutUsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        recyclerAboutUs.adapter = AboutUsAdapter(requireActivity(), GetAboutUsBackgroud())
        recyclerAboutUs.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun GetAboutUsBackgroud(): List<Image> {
        val images = mutableListOf<Image>()
        if (DataHolder.getGuestLoggedIn()?.acercaVisivel == "true"
            && DataHolder.getGuestLoggedIn()?.acercaBackground != null) {
            for (s in DataHolder.getGuestLoggedIn()?.acercaBackground!!) {
                images.add(Image(s))
            }
        }
        return images
    }
}