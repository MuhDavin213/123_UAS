 package com.example.theelektronik.ui.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.theelektronik.R

class EditActivity : AppCompatActivity() {
    private lateinit var produkId :EditText
    private lateinit var namaProduk :EditText
    private lateinit var jenisProduk :EditText
    private lateinit var jumlahProduk :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    }
}