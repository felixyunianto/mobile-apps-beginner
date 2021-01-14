package com.example.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wisataproject.adapters.WisataAdapter
import com.example.wisataproject.adapters.WisataAdapter.MyHolder
import com.example.wisataproject.contracts.WisataActiviyContract
import com.example.wisataproject.models.Wisata
import com.example.wisataproject.presenters.MainActivityPresenter
import com.example.wisataproject.utilities.WisataUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), WisataActiviyContract.View {
    private var presenter = MainActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkIsLoggedIn()
        presenter = MainActivityPresenter(this)
        Logout()
        intentCreate()
    }

    private fun intentCreate(){
        btnCreate.setOnClickListener{
            startActivity(Intent(this, CreateTourismActivity::class.java)).also { finish() }
        }
    }

    private fun Logout(){
        btnLogout.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java)).also{
                finish()
                WisataUtils.clearToken(this)
            }
        }
    }

    private fun getData (){
        WisataUtils.getToken(this)?.let { presenter?.allUser(it) }
    }

    override fun attachToRecycle(tourism: List<Wisata>) {
        rvTourism.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = WisataAdapter(tourism, this@MainActivity)
        }
    }

    override fun toast(message: String?) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {

    }

    override fun notConnect(message: String?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    private fun checkIsLoggedIn(){
        val token = WisataUtils.getToken(this@MainActivity)
        if(token == null || token.equals("UNDEFINED")){
            startActivity(Intent(this@MainActivity, LoginActivity::class.java).also { finish() })
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }




}