package com.example.theelektronik.model

data class Produk(
    val kode: String,
    val namaProduk: String,
    val jenisProduk: String,
    val jumlahProduk: String,
    val hargaProduk: String,
){
    constructor():this("","","","","")
}
