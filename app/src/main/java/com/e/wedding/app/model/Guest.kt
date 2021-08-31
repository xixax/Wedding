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
    //Image that will appear as pages. There will be a option to download
    @SerializedName("convitetext1")
    @Expose
    val convitetext1: String,
    @SerializedName("menuImagem")
    @Expose
    val menuImagem: String,
    @SerializedName("lembrancaImagem")
    @Expose
    val lembrancaImagem: String,

    //Text strings
    //Invite
    @SerializedName("convite")
    @Expose
    val convite: String,
    @SerializedName("conviteAceite")
    @Expose
    val conviteAceite: String,
    @SerializedName("conviteDetalhes")
    @Expose
    val conviteDetalhes: String,

    //Breakfast
    @SerializedName("pequenoAlmoco")
    @Expose
    val pequenoAlmoco: String,
    @SerializedName("pequenoAlmocoDetalhes")
    @Expose
    val pequenoAlmocoDetalhes: String,
    @SerializedName("googleMapsPequenoAlmoco")
    @Expose
    val googleMapsPequenoAlmoco: String,

    //Menu menu
    @SerializedName("menu")
    @Expose
    val menu: String,
    @SerializedName("menuDocumento")
    @Expose
    val menuDocumento: String,

    //About
    @SerializedName("acerca")
    @Expose
    val acerca: String,

    //ceremony
    @SerializedName("cerimonia")
    @Expose
    val cerimonia: String,

    //engagement
    @SerializedName("casamento")
    @Expose
    val casamento: String,

    //gift
    @SerializedName("presente")
    @Expose
    val presente: String

)