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

class WisataActivity : AppCompatActivity(), WisataActiviyContract.View {
    private var presenter = MainActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkIsLoggedIn()
        presenter = MainActivityPresenter(this)
//        Logout()
//        intentCreate()
    }

//    private fun intentCreate(){
//        btnCreate.setOnClickListener{
//            startActivity(Intent(this, CreateTourismActivity::class.java)).also { finish() }
//        }
//    }

    private fun getData (){
        WisataUtils.getToken(this)?.let { presenter?.allUser(it) }
    }

    override fun attachToRecycle(tourism: List<Wisata>) {
        rvTourism.apply {
            layoutManager = LinearLayoutManager(this@WisataActivity)
            adapter = WisataAdapter(tourism, this@WisataActivity)

        }
    }

    override fun toast(message: String?) {
        Toast.makeText(this@WisataActivity, message, Toast.LENGTH_LONG).show()
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
        val token = WisataUtils.getToken(this@WisataActivity)
        if(token == null || token.equals("UNDEFINED")){
            startActivity(Intent(this@WisataActivity, LoginActivity::class.java).also { finish() })
        }
    }
    override fun onResume() {
        super.onResume()
        getData()
    }
}