package com.example.theelektronik.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.theelektronik.data.ProdukRepositori
import com.example.theelektronik.ui.DetailProduk
import com.example.theelektronik.ui.UIStateProduk
import com.example.theelektronik.ui.toProduk

class AddViewModel(private val produkRepositori: ProdukRepositori): ViewModel(){
    /**
     * Berisi status Produki saat ini
     */
    var uiStateProduk by mutableStateOf(UIStateProduk())
        private set

    /* Fungsi untuk memvalidasi input */

    fun updateUiState(detailProduk: DetailProduk) {
        uiStateProduk=
            UIStateProduk(detailProduk = detailProduk,)
    }

    /*Fungsi untuk menyimpan data yang di-entry */
    suspend fun saveProduk(){
        produkRepositori.save(uiStateProduk.detailProduk.toProduk())
    }
}