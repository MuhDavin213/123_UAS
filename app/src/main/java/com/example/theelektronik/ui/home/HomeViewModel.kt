package com.example.theelektronik.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theelektronik.data.ProdukRepositori
import com.example.theelektronik.model.Produk
import com.example.theelektronik.ui.HomeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class ProdukUIState {
    data class Success(val produk: Flow<List<Produk>>) : ProdukUIState()
    object Error : ProdukUIState()
    object Loading : ProdukUIState()
}

class HomeViewModel(private val produkRepositori: ProdukRepositori) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    val homeUIState: StateFlow<HomeUIState> = produkRepositori.getAll()
        .filterNotNull()
        .map {produkList ->
            val filteredList = produkList.filter { produk ->
                produk.namaProduk.contains(_searchQuery.value, ignoreCase = true) ||
                        produk.jenisProduk.contains(_searchQuery.value, ignoreCase = true) ||
                        produk.hargaProduk.contains(_searchQuery.value, ignoreCase = true) ||
                        produk.jumlahProduk.contains(_searchQuery.value, ignoreCase = true)

            }
            HomeUIState (listProduk = filteredList) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()

        )
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

}
data class HomeUiState(
    val listKontak: List<Produk> = listOf()
)