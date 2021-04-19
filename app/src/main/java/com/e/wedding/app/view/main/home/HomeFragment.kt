package com.e.wedding.app.view.main.home

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.e.wedding.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.menu_recycler_item.view.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setup()

//        mActivityBinding = ActivityMytvSingleRecordingsManagementBinding.inflate(layoutInflater)
//        setContentView(mActivityBinding.root)



//        val thread = Thread {
//            try {
//
////                InetAddress.getByName("google.com")
////                val myImage: Bitmap = getBitmapFromURL("https://drive.google.com/u/0/uc?id=1WXc8HlPGVC4h6FovNqnIYgRtfIMAEOAs&export=download")!!
////                val dr: Drawable = BitmapDrawable(resources, myImage)
////                activity?.runOnUiThread {
////                    val homeLayout: ConstraintLayout = (activity?.findViewById(R.id.home_layout) ?: null)!!
////                    if(homeLayout!=null){
////                        homeLayout.background = dr
////                    }
////                }
//
//
//
//            } catch (e: Exception) {
//                activity?.runOnUiThread {
//                    showErrorNeutralMessage(
//                        resources.getString(R.string.internet_title_dialog_alert),
//                        resources.getString(
//                            R.string.internet_message_dialog_alert
//                        ),
//                        resources.getString(R.string.okay)
//                    )
//                }
//            }
//        }
//        thread.start()

        return binding.root
    }

    private fun setup(){
        Glide.with(this)
                .load("https://drive.google.com/u/0/uc?id=1WXc8HlPGVC4h6FovNqnIYgRtfIMAEOAs&export=download")
                .into(binding.homeBackground)
    }

    private fun showErrorNeutralMessage(title: String, msg: String, button_text: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setNeutralButton(button_text) { dialog, _ ->
            dialog.cancel()
            dialog.dismiss()
        }
        builder.show()
    }

    fun getBitmapFromURL(imageUrl: String?): Bitmap? {
        return try {
            val url = URL(imageUrl)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}