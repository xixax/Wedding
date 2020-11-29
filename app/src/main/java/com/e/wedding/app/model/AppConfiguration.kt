package com.e.wedding.app.model

data class AppConfiguration (
    val username: String,
    val password: String,
    val convidados: ArrayList<Guest>
)
