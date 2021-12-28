package com.e.wedding.app.view.ceremony

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.app.model.DataHolder
import com.e.wedding.databinding.FragmentCeremonyBinding


class CeremonyFragment : BaseFragment(R.layout.fragment_ceremony) {
    private val binding by viewBinding(FragmentCeremonyBinding::bind)
    private val viewModel by viewModel<CeremonyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        textCeremony.text = "Click here for map!"
        textCeremony.setOnClickListener {
            val uri =
                "http://maps.google.com/maps?q=loc:" + DataHolder.getAppConfig()?.cerimoniaLatitude + "," + DataHolder.getAppConfig()?.cerimoniaLongitude
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }
}