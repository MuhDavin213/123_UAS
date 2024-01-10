package com.example.theelektronik.data

import com.example.theelektronik.model.Produk
import kotlinx.coroutines.flow.Flow

interface ProdukRepositori{
    fun getAll(): Flow<List<Produk>>
    suspend fun save(kontak: Produk):String
    suspend fun update(kontak: Produk)
    suspend fun delete(produkKode: String)
    fun getKontakById(produkKode: String): Flow<Produk>
}