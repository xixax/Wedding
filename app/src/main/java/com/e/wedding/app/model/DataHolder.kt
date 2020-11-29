package com.e.wedding.app.model

//This will be the class were you store information that is global
object DataHolder {

    const val APP_MAIN_CONFIG_URL: String =
        "https://drive.google.com/file/d/1T7V41tbAWiRbgS6EDlXRgAc7Gd6h5IBn/view?usp=sharing"
    private var mAppConfiguration: AppConfiguration? = null

    fun setGuest(appConfig: AppConfiguration) {
        mAppConfiguration = appConfig
    }

    fun getGuest(): AppConfiguration? {
        return mAppConfiguration
    }
}