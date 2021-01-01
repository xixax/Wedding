package com.e.wedding.app.view.main.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.e.wedding.R
import com.e.wedding.app.model.DataHolder
import java.lang.Exception

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        //val textView: TextView = root.findViewById(R.id.text_login)
        //textView.text = DataHolder.getAppConfig()?.boda

        val submitLoginRequest: Button = root.findViewById(R.id.button_login)
        submitLoginRequest.setOnClickListener {

            try {
                val username: TextView = root.findViewById(R.id.username_input)
                val password: TextView = root.findViewById(R.id.password_input)
                val guests = DataHolder.getAppConfig()?.convidados
                val guest = guests?.firstOrNull {g -> g.username == username.text.toString() && g.password == password.text.toString() }

                if (guest != null) {
                    DataHolder.setGuestLoggedIn(guest)
                    val logintextview: TextView? = this.activity?.findViewById(R.id.button_login_main)
                    logintextview?.setText(R.string.button_text_logout)
                    val usernamemnu: TextView? = this.activity?.findViewById(R.id.guest_name)
                    usernamemnu?.setText(guest.username)
                    this.activity?.onBackPressed()
                } else {
                    this.activity?.runOnUiThread {
                        showErrorNeutralMessage(root,
                            resources.getString(R.string.error_wrong_user_details_title),
                            resources.getString(R.string.error_wrong_user_details_msg),
                            resources.getString(R.string.okay))
                    }
                }
            }catch (e:Exception)
            {
                this.activity?.runOnUiThread {
                    showErrorNeutralMessage(root,
                        resources.getString(R.string.error_login_title),
                        resources.getString(R.string.error_login_msg),
                        resources.getString(R.string.okay))
                }
            }

        }

        return root
    }

    private fun showErrorNeutralMessage(view:View,title: String, msg: String, button_text: String) {
        val builder = AlertDialog.Builder(view.context)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setNeutralButton(button_text) { dialog, _ ->
            dialog.cancel()
            dialog.dismiss()
        }
        builder.show()
    }
}