package com.example.theelektronik.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelektronik.data.ProdukRepositori
import com.example.theelektronik.ui.DetailProduk
import com.example.theelektronik.ui.UIStateProduk
import com.example.theelektronik.ui.toProduk
import com.example.theelektronik.ui.toUiStateProduk
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHandle: SavedStateHandle,
    private val repository: ProdukRepositori
) : ViewModel(){
    var produkUiState by mutableStateOf(UIStateProduk())
        private set

    private val produkId: String = checkNotNull(savedStateHandle[EditDestination.produkId])

    init {
        viewModelScope.launch {
            produkUiState =
                repository.getProdukById(produkId)
                    .filterNotNull()
                    .first()
                    .toUiStateProduk()
        }
    }

    fun updateUIState(detailProduk: DetailProduk) {
        produkUiState = produkUiState.copy(detailProduk = detailProduk)
    }

    suspend fun updateProduk() {
        repository.update(produkUiState.detailProduk.toProduk())

    }
}