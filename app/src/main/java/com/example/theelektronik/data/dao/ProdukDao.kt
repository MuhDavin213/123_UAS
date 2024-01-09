package com.example.theelektronik.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.theelektronik.data.entity.Produk

@Dao
interface ProdukDao {
    @Query("SELECT * FROM produk")
    fun getAll(): List<Produk>

    @Query("SELECT * FROM produk WHERE produkId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Produk>

    @Insert
    fun insertAll(vararg users: Produk)

    @Delete
    fun delete(user: Produk)
}