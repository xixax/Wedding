package com.e.wedding.app.view.gift

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.app.model.DataHolder
import com.e.wedding.app.model.Image
import com.e.wedding.app.view.menu.MenuAdapter
import com.e.wedding.databinding.FragmentGiftBinding

class GiftFragment : BaseFragment(R.layout.fragment_gift) {
    private val binding by viewBinding(FragmentGiftBinding::bind)
    private val viewModel by viewModel<GiftViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        recyclerGift.adapter = GiftAdapter(requireActivity(), GetGiftBackgroud())
        recyclerGift.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun GetGiftBackgroud(): List<Image> {
        val images = mutableListOf<Image>()
        if (DataHolder.getGuestLoggedIn()?.presenteVisivel == "true"
            && DataHolder.getGuestLoggedIn()?.presenteBackground != null) {
            for (s in DataHolder.getGuestLoggedIn()?.presenteBackground!!) {
                images.add(Image(s))
            }
        }
        return images
    }
}