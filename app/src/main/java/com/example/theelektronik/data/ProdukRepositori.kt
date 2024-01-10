package com.example.theelektronik.data

import android.content.ContentValues
import android.util.Log
import com.example.theelektronik.model.Produk
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface ProdukRepositori{
    fun getAll(): Flow<List<Produk>>
    suspend fun save(produk: Produk):String
    suspend fun update(produk: Produk)
    suspend fun delete(produkId: String)
    fun getProdukById(produkId: String): Flow<Produk>
}
class ProdukRepositoriImpl(private val firestore: FirebaseFirestore):ProdukRepositori{
    override fun getAll(): Flow<List<Produk>> = flow{
        val snapshot = firestore.collection("Produk")
            .orderBy("namaProduk", Query.Direction.ASCENDING)
            .get()
            .await()
        val produk = snapshot.toObjects(Produk::class.java)
        emit(produk)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(produk: Produk): String {
        return try {
            val documentReference = firestore.collection("Produk").add(produk).await()

            firestore.collection("Produk").document(documentReference.id)
                .set(produk.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception){
            Log.w(ContentValues.TAG,"Error adding document",e)
            "Gagal $e"
        }
    }

    override suspend fun update(produk: Produk) {
        firestore.collection("Produk").document(produk.id).set(produk).await()
    }

    override suspend fun delete(produkId: String) {
        firestore.collection("Produk").document(produkId).delete().await()
    }

    override fun getProdukById(produkId: String): Flow<Produk> {
        return flow {
            val snapshot = firestore.collection("Produk").document(produkId).get().await()
            val produk = snapshot.toObject(Produk::class.java)
            produk?.let {
                emit(it)
            }?:run{
                emit(Produk())
            }
        }
    }

}