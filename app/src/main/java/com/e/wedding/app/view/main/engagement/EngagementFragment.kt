package com.e.wedding.app.view.main.engagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.wedding.R
import com.e.wedding.app.model.DataHolder
import com.e.wedding.app.view.main.invite.InviteViewModel

class EngagementFragment : Fragment() {

    private lateinit var engagementViewModel: EngagementViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        engagementViewModel = ViewModelProvider(this).get(EngagementViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_engagement, container, false)
        val textView: TextView = root.findViewById(R.id.text_engagement)
        textView.text = DataHolder.getGuestLoggedIn()?.convitetext1 ?: "User ainda nao fez login"
        return root
    }
}