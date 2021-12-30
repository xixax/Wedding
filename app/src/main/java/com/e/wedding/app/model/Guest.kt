package com.e.wedding.app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Guest(
    @SerializedName("username")
    @Expose
    val username: String,
    @SerializedName("password")
    @Expose
    val password: String,

    //Text strings
    //Invite
    @SerializedName("conviteVisivel")
    @Expose
    val conviteVisivel: String,
    @SerializedName("conviteDocumento")
    @Expose
    val conviteDocumento: String,
    @SerializedName("conviteBackground")
    @Expose
    val conviteBackground: List<String>,

    //Breakfast
    @SerializedName("pequenoAlmocoVisivel")
    @Expose
    val pequenoAlmocoVisivel: String,
    @SerializedName("pequenoAlmocoImagen")
    @Expose
    val pequenoAlmocoImagen: String,
    @SerializedName("googleMapsPequenoAlmocoUrl")
    @Expose
    val googleMapsPequenoAlmocoUrl: String,

    //Menu menu
    @SerializedName("menuVisivel")
    @Expose
    val menuVisivel: String,
    @SerializedName("menuDocumento")
    @Expose
    val menuDocumento: String,
    @SerializedName("menuBackground")
    @Expose
    val menuBackground: List<String>,

    //About
    @SerializedName("acercaVisivel")
    @Expose
    val acercaVisivel: String,
    @SerializedName("acercaBackground")
    @Expose
    val acercaBackground: List<String>,

    //ceremony
    @SerializedName("cerimoniaVisivel")
    @Expose
    val cerimoniaVisivel: String,
    @SerializedName("cerimoniaImagen")
    @Expose
    val cerimoniaImagen: String,
    @SerializedName("googleMapsCerimoniaUrl")
    @Expose
    val googleMapsCerimoniaUrl: String,

    //engagement
    @SerializedName("casamentoVisivel")
    @Expose
    val casamentoVisivel: String,
    @SerializedName("casamentoImagen")
    @Expose
    val casamentoImagen: String,
    @SerializedName("googleMapsCasamentoUrl")
    @Expose
    val googleMapsCasamentoUrl: String,

    //gift
    @SerializedName("presenteVisivel")
    @Expose
    val presenteVisivel: String,
    @SerializedName("presenteBackground")
    @Expose
    val presenteBackground: List<String>,

    //Gallery
    @SerializedName("galeriaVisivel")
    @Expose
    val galeriaVisivel: String,
    @SerializedName("galeriaBackground")
    @Expose
    val galeriaBackground: List<String>,

    //Schedule
    @SerializedName("horarioVisivel")
    @Expose
    val horarioVisivel: String,
    @SerializedName("horarioDocumento")
    @Expose
    val horarioDocumento: String,
    @SerializedName("horarioBackground")
    @Expose
    val horarioBackground: List<String>,
)