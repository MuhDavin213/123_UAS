package com.example.theelektronik.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.theelektronik.R
import com.example.theelektronik.model.Produk
import com.example.theelektronik.navigation.DestinasiNavigasi
import com.example.theelektronik.ui.PenyediaViewModel
import com.example.theelektronik.ui.ProdukTopAppBar


object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Produk"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier,
        topBar = {
            ProdukTopAppBar(
                title = "Produk",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->
        val uiStateProduk by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemProduk = uiStateProduk.listProduk,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onProdukClick = onDetailClick
        )
        // Menambahkan gambar dari drawable
        Image(
            painter = painterResource(id = R.drawable.elektro), // Ganti dengan nama gambar Anda
            contentDescription = "Deskripsi gambar",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Composable
fun BodyHome(
    itemProduk: List<Produk>,
    modifier: Modifier = Modifier,
    onProdukClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemProduk.isEmpty()) {
            Text(
                text = "Tidak ada data Produk",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListProduk(
                itemProduk = itemProduk,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onProdukClick(it.id) }
            )
        }
    }
}

@Composable
fun ListProduk(
    itemProduk: List<Produk>,
    modifier: Modifier = Modifier,
    onItemClick: (Produk) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemProduk, key = { it.id }) { produk ->
            DataProduk(
                produk = produk,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(produk) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataProduk(
    produk: Produk,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = produk.namaProduk,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                )
                Text(
                    text = produk.jenisProduk,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = produk.jumlahProduk,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = produk.hargaProduk,
                style = MaterialTheme.typography.titleMedium)
        }
    }
}