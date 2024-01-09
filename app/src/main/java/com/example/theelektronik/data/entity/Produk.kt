package com.example.theelektronik.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Produk(
    @PrimaryKey(autoGenerate = true) var uid : Int? = null,
    @ColumnInfo(name = "nama_produk") var namaProduk : String?,
    @ColumnInfo(name = "jenis_produk") var jenisProduk : String?,
    @ColumnInfo(name = "jumlah_produk") var jumlahProduk : String?

)
