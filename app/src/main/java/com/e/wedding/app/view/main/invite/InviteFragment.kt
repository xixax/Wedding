package com.e.wedding.app.view.main.invite

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.wedding.R
import com.e.wedding.app.model.DataHolder
import com.e.wedding.app.model.Image
import com.e.wedding.app.view.main.menu.MenuAdapter

class InviteFragment : Fragment() {

    private lateinit var inviteViewModel: InviteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        inviteViewModel = ViewModelProvider(this).get(InviteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_invite, container, false)

        val recyclerMenu: RecyclerView = root.findViewById(R.id.recycler_invite)
        recyclerMenu.adapter = InviteAdapter(requireActivity(), GetInviteBackgroud())
        recyclerMenu.layoutManager = LinearLayoutManager(requireActivity())

        return root
    }

    private fun GetInviteBackgroud():List<Image>{
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