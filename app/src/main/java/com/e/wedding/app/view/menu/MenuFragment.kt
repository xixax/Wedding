package com.e.wedding.app.view.menu

import android.Manifest
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.wedding.R
import com.e.wedding.app.base.BaseFragment
import com.e.wedding.app.base.viewBinding
import com.e.wedding.app.base.viewModel
import com.e.wedding.app.model.DataHolder
import com.e.wedding.app.model.Image
import com.e.wedding.databinding.FragmentMenuBinding
import java.net.InetAddress


class MenuFragment : BaseFragment(R.layout.fragment_menu) {
    private val binding by viewBinding(FragmentMenuBinding::bind)
    private val viewModel by viewModel<MenuViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        menuPdfDownload.setOnClickListener {
            val thread = Thread {
                try {
                    InetAddress.getByName("google.com")
                    menuPdfDownload()
                } catch (e: Exception) {
                    activity?.runOnUiThread {
                        showErrorNeutralMessage(
                            resources.getString(R.string.internet_title_dialog_alert),
                            resources.getString(
                                R.string.internet_message_dialog_alert
                            ),
                            resources.getString(R.string.okay)
                        )
                    }
                }
            }
            thread.start()
        }

        recyclerMenu.adapter = MenuAdapter(requireActivity(), GetMenuBackgroud())
        recyclerMenu.layoutManager = LinearLayoutManager(requireActivity())
    }


    private fun GetMenuBackgroud(): List<Image> {
        val images = mutableListOf<Image>()
        images.add(Image("https://drive.google.com/u/0/uc?id=1kzw6clDJ-uoseMpKB8dTqml9yjGpRIvV&export=download"))
        images.add(Image("https://drive.google.com/u/0/uc?id=1MXVCqmt645Ww4VT5As2dA2PVrb2V4-EJ&export=download"))
        return images
    }

    private fun menuPdfDownload() {
        try {
            if (DataHolder.getGuestLoggedIn() != null) {
                if (activity?.let {
                        ActivityCompat.checkSelfPermission(
                            it,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    } != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED) {
                    activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1) }
                    // this will request for permission when permission is not true
                } else {
                    // Download code here
                    val downloadmanager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
                    val uri: Uri = Uri.parse(DataHolder.getGuestLoggedIn()?.menuDocumento)

                    val request = DownloadManager.Request(uri)
                    request.setTitle("JD_Wedding_Menu")
                    request.setDescription("Downloading")
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "JD_Wedding_Menu.pdf")
                    downloadmanager!!.enqueue(request)
                }
            } else {
                activity?.runOnUiThread {
                    showErrorNeutralMessage(
                        resources.getString(R.string.download_menu_not_found_title),
                        resources.getString(
                            R.string.download_menu_not_found_text
                        ),
                        resources.getString(R.string.okay)
                    )
                }
            }
        } catch (e: Exception) {
            activity?.runOnUiThread {
                showErrorNeutralMessage(
                    resources.getString(R.string.download_menu_not_found_title),
                    resources.getString(
                        R.string.download_menu_not_found_text
                    ),
                    resources.getString(R.string.okay)
                )
            }
        }
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