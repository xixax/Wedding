package com.e.wedding.app.model

data class Guest(
    val username: String,
    val password: String,
    //Image that will appear as pages. There will be a option to download
    val conviteImagem: String,
    val menuImagem: String,
    val lembrancaImagem: String,

    //Text strings
    //Invite
    val conviteAceite: String,
    val conviteDetalhes: String,

    //Breakfast
    val pequenoAlmoco: String,
    val pequenoAlmocoDetalhes: String,
    val googleMapsPequenoAlmoco: String
)