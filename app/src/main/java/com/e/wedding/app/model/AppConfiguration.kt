package com.e.wedding.app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AppConfiguration(
    @SerializedName("googleMapsBoda")
    @Expose
    val boda: String,

    @SerializedName("googleMapsCerimoniaLatitude")
    @Expose
    val cerimoniaLatitude: String,

    @SerializedName("googleMapsCerimoniaLongitude")
    @Expose
    val cerimoniaLongitude: String,

    @SerializedName("convidados")
    @Expose
    val convidados: List<Guest>
)
