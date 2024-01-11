package com.example.theelektronik.ui.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.theelektronik.R
import com.example.theelektronik.navigation.DestinasiNavigasi
import com.example.theelektronik.ui.DetailProduk
import com.example.theelektronik.ui.PenyediaViewModel
import com.example.theelektronik.ui.ProdukTopAppBar
import com.example.theelektronik.ui.UIStateProduk
import kotlinx.coroutines.launch

object DestinasiProduk : DestinasiNavigasi{
    override val route = "item_entry"
    override val titleRes = "Tambahkan Produk"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        topBar = {
            ProdukTopAppBar(
                title = DestinasiProduk.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        AddProdukBody(
            uiStateProduk = viewModel.uiStateProduk,
            onProdukValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveProduk()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        )
    }
}

@Composable
fun AddProdukBody(
    uiStateProduk: UIStateProduk,
    onProdukValueChange: (DetailProduk) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_large))
            .fillMaxWidth()
    ){
        FormInput(
            detailProduk = uiStateProduk.detailProduk,
            onValueChange = onProdukValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_large)))
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(stringResource(R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    detailProduk: DetailProduk,
    modifier: Modifier = Modifier,
    onValueChange: (DetailProduk) -> Unit = {},
    enabled: Boolean = true
) {
    Column (
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ){
        OutlinedTextField(
            value = detailProduk.namaProduk,
            onValueChange = {onValueChange(detailProduk.copy(namaProduk = it)) },
            label = { Text(stringResource(R.string.nama_barang)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodySmall
        )
        OutlinedTextField(
            value = detailProduk.jenisProduk,
            onValueChange = {onValueChange(detailProduk.copy(jenisProduk = it)) },
            label = { Text(stringResource(R.string.jenis_barang)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodySmall
        )
        OutlinedTextField(
            value = detailProduk.jumlahProduk,
            onValueChange = {onValueChange(detailProduk.copy(jumlahProduk = it)) },
            label = { Text(stringResource(R.string.jumlah_barang)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodySmall
        )
        OutlinedTextField(
            value = detailProduk.hargaProduk,
            onValueChange = {onValueChange(detailProduk.copy(hargaProduk = it)) },
            label = { Text(stringResource(R.string.harga_barang)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodySmall,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }
}