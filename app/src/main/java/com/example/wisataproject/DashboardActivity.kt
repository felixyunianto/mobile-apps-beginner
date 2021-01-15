package com.example.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wisataproject.contracts.WisataActiviyContract
import com.example.wisataproject.models.Wisata
import com.example.wisataproject.presenters.MainActivityPresenter
import com.example.wisataproject.utilities.WisataUtils
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        btn_wisata.setOnClickListener {
            startActivity(Intent(this, WisataActivity::class.java))
        }
        setting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }
        Logout()
    }
    private fun Logout(){
        Logout.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java)).also{
                WisataUtils.clearToken(this)
                finish()
            }
        }
    }
}