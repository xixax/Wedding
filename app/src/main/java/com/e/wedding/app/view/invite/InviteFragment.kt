package com.e.wedding.app.view.invite

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.app.model.Image
import com.e.wedding.databinding.FragmentInviteBinding

class InviteFragment : BaseFragment(R.layout.fragment_invite) {
    private val binding by viewBinding(FragmentInviteBinding::bind)
    private val viewModel by viewModel<InviteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        recyclerInvite.adapter = InviteAdapter(requireActivity(), GetInviteBackgroud())
    }

    private fun GetInviteBackgroud(): List<Image> {
        val images = mutableListOf<Image>()
        images.add(Image("https://drive.google.com/u/0/uc?id=1NoC1XYte0-VCsZXuEGAg92bLtHa1qFDA&export=download"))
        images.add(Image("https://drive.google.com/u/0/uc?id=1sN7S9smNAfNGHtuxZB_OujnPdIzvCo0W&export=download"))
        return images
    }

    private fun showErrorNeutralMessage(title: String, msg: String, button_text: String) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setNeutralButton(button_text) { dialog, _ ->
            dialog.cancel()
            dialog.dismiss()
        }
        builder.show()
    }
}