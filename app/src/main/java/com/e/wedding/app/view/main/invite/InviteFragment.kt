package com.e.wedding.app.view.main.invite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.wedding.R
import com.e.wedding.app.model.DataHolder

class InviteFragment : Fragment() {

    private lateinit var inviteViewModel: InviteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        inviteViewModel = ViewModelProvider(this).get(InviteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_invite, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        textView.text = DataHolder.getGuestLoggedIn()?.convitetext1 ?: "User ainda nao fez login"
        return root
    }
}