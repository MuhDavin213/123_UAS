package com.example.theelektronik.ui

import com.example.theelektronik.model.Produk

data class UIStateProduk(
    val detailProduk: DetailProduk= DetailProduk(),
)

data class  DetailProduk(
    val id: String = "",
    val namaProduk : String= "",
    val jenisProduk : String= "",
    val jumlahProduk : String= "",
    val hargaProduk : String= "",
)

/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya*/
fun DetailProduk.toProduk(): Produk = Produk(
    id = id,
    namaProduk = namaProduk,
    jenisProduk = jenisProduk,
    jumlahProduk = jumlahProduk,
    hargaProduk = hargaProduk
)

data class DetailUIState(
    val addEvent: DetailProduk = DetailProduk(),
)

fun Produk.toDetailProduk(): DetailProduk =
    DetailProduk(
        id = id,
        namaProduk = namaProduk,
        jenisProduk = jenisProduk,
        jumlahProduk = jumlahProduk,
        hargaProduk = hargaProduk
    )

fun Produk.toUiStateProduk(): UIStateProduk = UIStateProduk(
    detailProduk = this.toDetailProduk()
)

data class HomeUIState(
    val listProduk: List<Produk> = listOf(),
    val dataLength: Int = 0
)