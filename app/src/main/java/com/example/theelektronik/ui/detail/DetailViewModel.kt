package com.example.theelektronik.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelektronik.data.ProdukRepositori
import com.example.theelektronik.ui.DetailUIState
import com.example.theelektronik.ui.toDetailProduk
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val repository: ProdukRepositori
): ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val produkId: String = checkNotNull(savedStateHandle[DetailDestination.produkId])

    val uiState: StateFlow<DetailUIState> =
        repository.getProdukById(produkId)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailProduk())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deleteProduk() {
        repository.delete(produkId)

    }

}