package com.example.theelektronik.data

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer{
    val produkRepositori:ProdukRepositori
}
class ProdukContainer : AppContainer {
    private val firestore:FirebaseFirestore = FirebaseFirestore.getInstance()

    override val produkRepositori: ProdukRepositori by lazy {
        ProdukRepositoriImpl(firestore)
    }
}