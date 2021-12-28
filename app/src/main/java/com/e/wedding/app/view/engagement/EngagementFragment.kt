package com.e.wedding.app.view.engagement

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.app.model.DataHolder
import com.e.wedding.app.view.gift.GiftViewModel
import com.e.wedding.databinding.FragmentEngagementBinding

class EngagementFragment : BaseFragment(R.layout.fragment_engagement) {
    private val binding by viewBinding(FragmentEngagementBinding::bind)
    private val viewModel by viewModel<GiftViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        Glide.with(this)
            .load(DataHolder.getGuestLoggedIn()?.casamentoImagen)
            .into(binding.engagementBackground)

        binding.engagementBackground.setOnClickListener {
            val uri = DataHolder.getGuestLoggedIn()?.googleMapsCasamentoUrl
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }
}