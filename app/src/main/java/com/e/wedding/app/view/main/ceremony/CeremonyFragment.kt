package com.e.wedding.app.view.main.ceremony

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.wedding.R
import com.e.wedding.app.model.DataHolder
import java.util.*


class CeremonyFragment : Fragment() {

    private lateinit var ceremonyViewModel: CeremonyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ceremonyViewModel = ViewModelProvider(this).get(CeremonyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ceremony, container, false)
        val text: TextView = root.findViewById(R.id.text_ceremony)
        text.text= "Click here for map!"

        text.setOnClickListener{
            val uri =
                "http://maps.google.com/maps?q=loc:" + DataHolder.getAppConfig()?.cerimoniaLatitude + "," + DataHolder.getAppConfig()?.cerimoniaLongitude
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)

        }
        return root
    }

}