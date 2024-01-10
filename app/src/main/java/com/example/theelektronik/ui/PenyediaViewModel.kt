package com.example.theelektronik.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.theelektronik.ProdukApplication
import com.example.theelektronik.ui.add.AddViewModel
import com.example.theelektronik.ui.detail.DetailViewModel
import com.example.theelektronik.ui.edit.EditViewModel
import com.example.theelektronik.ui.home.HomeViewModel

fun CreationExtras.aplikasiProduk():ProdukApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ProdukApplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasiProduk().container.produkRepositori
            )
        }
        initializer {
            AddViewModel(aplikasiProduk().container.produkRepositori
            )
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiProduk().container.produkRepositori
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiProduk().container.produkRepositori
            )
        }


    }
}