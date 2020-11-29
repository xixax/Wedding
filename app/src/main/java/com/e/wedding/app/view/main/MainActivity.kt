package com.e.wedding.app.view.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.e.wedding.R
import com.e.wedding.app.model.AppConfiguration
import com.e.wedding.app.model.DataHolder
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import java.io.*
import java.net.InetAddress
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //DataHolder.setGuest(Guest("Teste","1234"))
        initializeAppConfig()

        setupDrawerLayout()
    }

    fun initializeAppConfig(){
        val thread = Thread {
            try {
                InetAddress.getByName("google.com")
                downloadParseConfigFile(DataHolder.APP_MAIN_CONFIG_URL)
                initAppConfiguration()
            } catch (e: Exception) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.internet_title_dialog_alert)
                builder.setMessage(R.string.internet_message_dialog_alert)
                builder.setNeutralButton(R.string.okay){ dialog, _ ->
                    dialog.cancel()
                    dialog.dismiss()
                }
                builder.show()
            }
        }
        thread.start()
    }

    fun downloadParseConfigFile(urlpath: String?) {
        try {
            val url = URL(urlpath)
            val ucon = url.openConnection()
            ucon.readTimeout = 5000
            ucon.connectTimeout = 10000

            val `is` = ucon.getInputStream()
            val inStream = BufferedInputStream(`is`, 1024 * 5)

            val file =
                File(this.getDir("Config", MODE_PRIVATE).toString() + "/Configuration.json")
            if (file.exists()) {
                file.delete()
            }
            file.createNewFile()
            val outStream = FileOutputStream(file)
            val buff = ByteArray(5 * 1024)
            var len: Int
            while (inStream.read(buff).also { len = it } != -1) {
                outStream.write(buff, 0, len)
            }
            outStream.flush()
            outStream.close()
            inStream.close()

        } catch (e: java.lang.Exception) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.config_file_title_dialog_alert)
            builder.setMessage(R.string.config_file_message_dialog_alert)
            builder.setNeutralButton(R.string.okay){ dialog, _ ->
                dialog.cancel()
                dialog.dismiss()
            }
            builder.show()
        }
    }

    fun initAppConfiguration(){
        val path = this.getDir("Config", MODE_PRIVATE).toString() + "/Configuration.json"
        val bufferedReader = BufferedReader(FileReader(path))

        val gson = Gson()
        val aux= gson.fromJson(bufferedReader , AppConfiguration::class.java)
        val aux1=aux
    }

    private fun setupDrawerLayout(){
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
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
}