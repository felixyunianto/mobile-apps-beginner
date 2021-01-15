package com.example.wisataproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.wisataproject.models.Wisata

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val user = getUser()
         val nama = findViewById<TextView>(R.id.namawisata)
         val lokasi = findViewById<TextView>(R.id.location)
         val keterangan = findViewById<TextView>(R.id.deskripsi)
        user?.let {
            nama.text=it.name
            lokasi.text=it.location
            keterangan.text=it.description
        }
    }
    private fun getUser() = intent.getParcelableExtra<Wisata>("wisata")
}