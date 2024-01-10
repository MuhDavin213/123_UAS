package com.example.theelektronik.ui.edit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.theelektronik.navigation.DestinasiNavigasi
import com.example.theelektronik.ui.PenyediaViewModel
import com.example.theelektronik.ui.ProdukTopAppBar
import com.example.theelektronik.ui.add.AddProdukBody
import kotlinx.coroutines.launch

object EditDestination : DestinasiNavigasi.DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes ="Edit Produk"
    const val produkId = "itemId"
    val routeWithArgs = "${EditDestination.route}/{$produkId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            ProdukTopAppBar(
                title =EditDestination.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        AddProdukBody(
            uiStateProduk = viewModel.produkUiState,
            onProdukValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateProduk()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}