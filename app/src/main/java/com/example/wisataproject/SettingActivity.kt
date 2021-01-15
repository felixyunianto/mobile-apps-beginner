package com.example.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wisataproject.adapters.WisataAdapter
import com.example.wisataproject.contracts.WisataActiviyContract
import com.example.wisataproject.models.Wisata
import com.example.wisataproject.presenters.MainActivityPresenter
import com.example.wisataproject.utilities.WisataUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity(),WisataActiviyContract.View {
    private var presenter = MainActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        presenter = MainActivityPresenter(this)
        getData()
        create()
    }

    override fun attachToRecycle(tourism: List<Wisata>) {
        rvTourism1.apply {
            layoutManager = LinearLayoutManager(this@SettingActivity)
            adapter = WisataAdapter(tourism, this@SettingActivity)
        }
    }

    private fun create(){
        btnCreate.setOnClickListener{ startActivity(Intent(this, CreateTourismActivity::class.java)).also { finish() }
        }
    }
    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {
        TODO("Not yet implemented")
    }

    override fun notConnect(message: String?) {
        TODO("Not yet implemented")
    }
    private fun getData (){
        WisataUtils.getToken(this)?.let { presenter?.allUser(it) }
    }
    override fun onResume() {
        super.onResume()
        getData()
    }
}