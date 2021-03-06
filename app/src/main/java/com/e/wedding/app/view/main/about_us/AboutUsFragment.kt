package com.e.wedding.app.view.main.about_us

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.wedding.R
import com.e.wedding.app.model.DataHolder

class AboutUsFragment : Fragment() {

    private lateinit var aboutUsViewModel: AboutUsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        aboutUsViewModel = ViewModelProvider(this).get(AboutUsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_engagement, container, false)
        val textView: TextView = root.findViewById(R.id.text_engagement)
        textView.text = DataHolder.getGuestLoggedIn()?.convitetext1 ?: "User ainda nao fez login"
        return root
    }
}