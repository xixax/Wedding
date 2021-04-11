package com.e.wedding.app.view.main.menu

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

class MenuFragment : Fragment() {

    private lateinit var menuViewModel: MenuViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_menu, container, false)
        val textView: TextView = root.findViewById(R.id.text_menu)
        textView.text = DataHolder.getGuestLoggedIn()?.convitetext1 ?: "User ainda nao fez login"
        return root
    }
}