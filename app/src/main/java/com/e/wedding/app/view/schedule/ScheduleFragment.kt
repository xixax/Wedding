package com.e.wedding.app.view.schedule

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
import com.e.wedding.app.view.invite.ScheduleViewModel
import com.e.wedding.databinding.FragmentScheduleBinding
import java.net.InetAddress

class ScheduleFragment : BaseFragment(R.layout.fragment_schedule) {
    private val binding by viewBinding(FragmentScheduleBinding::bind)
    private val viewModel by viewModel<ScheduleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        schedulePdfDownload.setOnClickListener {
            val thread = Thread {
                try {
                    InetAddress.getByName("google.com")
                    schedulePdfDownload()
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

        recyclerSchedule.adapter = ScheduleAdapter(requireActivity(), GetScheduleBackgroud())
        recyclerSchedule.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun GetScheduleBackgroud(): List<Image> {
        val images = mutableListOf<Image>()
        if (DataHolder.getGuestLoggedIn()?.horarioVisivel == "true"
            && DataHolder.getGuestLoggedIn()?.horarioBackground != null) {
            for (s in DataHolder.getGuestLoggedIn()?.horarioBackground!!) {
                images.add(Image(s))
            }
        }
        //add error message
        return images
    }

    private fun schedulePdfDownload() {
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
                    val uri: Uri = Uri.parse(DataHolder.getGuestLoggedIn()?.horarioDocumento)

                    val request = DownloadManager.Request(uri)
                    request.setTitle("JD_Wedding_Invite")
                    request.setDescription("Downloading")
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "JD_Wedding_Invite.pdf")
                    downloadmanager!!.enqueue(request)
                }
            } else {
                activity?.runOnUiThread {
                    showErrorNeutralMessage(
                        resources.getString(R.string.download_invite_not_found_title),
                        resources.getString(
                            R.string.download_invite_not_found_text
                        ),
                        resources.getString(R.string.okay)
                    )
                }
            }
        } catch (e: Exception) {
            activity?.runOnUiThread {
                showErrorNeutralMessage(
                    resources.getString(R.string.download_invite_not_found_title),
                    resources.getString(
                        R.string.download_invite_not_found_text
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