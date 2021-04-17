package com.e.wedding.app.view.main.breakfast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.wedding.R
import com.e.wedding.app.model.DataHolder

class BreakfastFragment : Fragment() {

    private lateinit var breakfastViewModel: BreakfastViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        breakfastViewModel = ViewModelProvider(this).get(BreakfastViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_breakfast, container, false)
        val textView: TextView = root.findViewById(R.id.text_breakfast)
        textView.text = DataHolder.getGuestLoggedIn()?.convitetext1 ?: "User ainda nao fez login"
        return root
    }
}