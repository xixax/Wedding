package com.e.wedding.app.di

import com.e.wedding.app.App

/**
 * Only purpose of these modules is to make use of Kotlin delegation.
 */
interface AppComponent {
    val app: App
}