package com.e.wedding.app.model

//This will be the class were you store information that is global
object DataHolder {
    private lateinit var mGuest: Guest

    fun setGuest(guest: Guest) {
        mGuest = guest
    }

    fun getGuest(): Guest? {
        return mGuest
    }
}