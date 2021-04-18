package com.e.wedding.app.view.main.gift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.wedding.R
import com.e.wedding.app.model.DataHolder
import com.e.wedding.app.view.main.engagement.EngagementViewModel

class GiftFragment : Fragment() {

    private lateinit var giftViewModel: GiftViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        giftViewModel = ViewModelProvider(this).get(GiftViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gift, container, false)
        val textView: TextView = root.findViewById(R.id.text_gift)
        textView.text = DataHolder.getGuestLoggedIn()?.convitetext1 ?: "User ainda nao fez login"
        return root
    }
}