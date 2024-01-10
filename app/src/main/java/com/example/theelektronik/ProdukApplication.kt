package com.example.theelektronik

import android.app.Application
import com.example.theelektronik.data.AppContainer
import com.example.theelektronik.data.ProdukContainer

class ProdukApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = ProdukContainer()
    }
}