package com.e.wedding.app.view.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.e.wedding.R
import com.e.wedding.app.api.GetAppConfigService
import com.e.wedding.app.model.AppConfiguration
import com.e.wedding.app.model.DataHolder
import com.e.wedding.app.utils.Values
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetAddress


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeAppConfig()

        setupDrawerLayout()
    }

    private fun initializeAppConfig() {
        val thread = Thread {
            try {
                InetAddress.getByName("google.com")
                downloadParseConfigFile()
            } catch (e: Exception) {
                runOnUiThread {
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

    private fun downloadParseConfigFile() {

        val retrofit = Retrofit.Builder()
            .baseUrl(Values.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: GetAppConfigService = retrofit.create(GetAppConfigService::class.java)
        val appConfig: Call<AppConfiguration> = service.appConfig
        appConfig.enqueue(object : Callback<AppConfiguration> {
            override fun onResponse(
                    call: Call<AppConfiguration>,
                    response: Response<AppConfiguration>
            ) {
                val appBarConfiguration = response.body()
                if (appBarConfiguration != null) {
                    DataHolder.setAppConfig(appBarConfiguration)
                } else {
                    showErrorNeutralMessage(
                            resources.getString(R.string.config_file_title_dialog_alert),
                            resources.getString(
                                    R.string.config_file_message_dialog_alert
                            ),
                            resources.getString(R.string.okay)
                    )
                }
            }

            override fun onFailure(call: Call<AppConfiguration>, t: Throwable) {
                showErrorNeutralMessage(
                        resources.getString(R.string.config_file_title_dialog_alert),
                        resources.getString(
                                R.string.config_file_message_dialog_alert
                        ),
                        resources.getString(R.string.okay)
                )
            }

        })
    }

    private fun showErrorNeutralMessage(title: String, msg: String, button_text: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setNeutralButton(button_text) { dialog, _ ->
            dialog.cancel()
            dialog.dismiss()
        }
        builder.show()
    }

    private fun setupDrawerLayout() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.nav_home, R.id.nav_invite, R.id.nav_ceremony, R.id.nav_engagement, R.id.nav_food_menu,
                        R.id.nav_gift, R.id.nav_about_us, R.id.nav_breakfast
                ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val acc: DrawerListener = object : DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // Action to onDrawerSlider
                val userNameMenu: TextView? = findViewById(R.id.guest_name)
                userNameMenu?.text = DataHolder.getGuestLoggedIn()?.username ?: resources.getString(R.string.menu_guest_name)
            }

            override fun onDrawerOpened(drawerView: View) {
                // Action to onDrawerOpened
            }

            override fun onDrawerClosed(drawerView: View) {
                // Action to onDrawerClosed
            }

            override fun onDrawerStateChanged(newState: Int) {
                // Action to onDrawerStateChanged
            }
        }

        drawerLayout.addDrawerListener(acc);

    }

    fun onLoginClick(view: View)
    {
        val logintextview: TextView = view.findViewById(R.id.button_login_main)
        when (logintextview.text) {
            resources.getString(R.string.button_text_login) -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.button_text_login)

                val viewInflated: View = LayoutInflater.from(this).inflate(R.layout.content_login_layout, findViewById(android.R.id.content), false)
                val username = viewInflated.findViewById<View>(R.id.username_input) as EditText
                val password = viewInflated.findViewById<View>(R.id.password_input) as EditText
                val errorLogin = viewInflated.findViewById<View>(R.id.textView_FailedLogin) as TextView
                builder.setView(viewInflated)

                builder.setPositiveButton(R.string.button_text_login, null)// { dialog,
                builder.setNegativeButton(R.string.button_text_cancel) { dialog, _ -> dialog.cancel() }

                val dialog = builder.create()
                dialog.setOnShowListener {
                    val button: Button = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
                    button.setOnClickListener {
                        try {
                            val guests = DataHolder.getAppConfig()?.convidados
                            val guest = guests?.firstOrNull { g -> g.username == username.text.toString() && g.password == password.text.toString() }

                            if (guest != null) {
                                DataHolder.setGuestLoggedIn(guest)
                                val loginTextView: TextView? = findViewById(R.id.button_login_main)
                                loginTextView?.setText(R.string.button_text_logout)

                                val navController = findNavController(R.id.nav_host_fragment)
                                navController.navigateUp() // to clear previous navigation history
                                navController.navigate(R.id.nav_home)

                                dialog.dismiss()
                            } else {
                                errorLogin.text = resources.getString(R.string.error_wrong_user_details_msg)
                                errorLogin.visibility = View.VISIBLE
                            }
                        } catch (e: java.lang.Exception) {
                            errorLogin.text = resources.getString(R.string.error_login_msg)
                            errorLogin.visibility = View.VISIBLE
                        }
                    }
                }
                dialog.show()

            }
            resources.getString(R.string.button_text_logout) -> {
                DataHolder.setGuestLoggedIn(null)
                logintextview.setText(R.string.button_text_login)
                val navController = findNavController(R.id.nav_host_fragment)
                navController.navigateUp() // to clear previous navigation history
                navController.navigate(R.id.nav_home)
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}