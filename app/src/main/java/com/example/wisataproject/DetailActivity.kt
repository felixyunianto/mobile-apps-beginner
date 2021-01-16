package com.example.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.wisataproject.contracts.WisataActiviyContract
import com.example.wisataproject.models.Wisata
import com.example.wisataproject.presenters.DeleteActivityPresenter
import com.example.wisataproject.utilities.WisataUtils
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), WisataActiviyContract.ViewDelete {
    private var presenter = DeleteActivityPresenter(this)
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
        presenter = DeleteActivityPresenter(this)
        editData();
        Delete()
    }

    private fun editData(){
        btnEdit.setOnClickListener{
            val user = getUser()
            val intent = Intent(this, UpdateActivity::class.java)
            println("User "+ user)
            intent.putExtra("ID_USER", user?.id)
            intent.putExtra("NAME", user?.name)
            intent.putExtra("LOCATION", user?.location)
            intent.putExtra("DESCRIPTION", user?.description)

            startActivity(intent)
        }
    }

    private fun Delete(){
        btnDelete.setOnClickListener{
            val data = getUser();
            val id = data?.id.toString().toInt()
            val token = WisataUtils.getToken(this)
            token?.let { it1 -> presenter.delete(id, it1) }
        }
    }

    private fun getUser() = intent.getParcelableExtra<Wisata>("wisata")
    override fun success(message: String?) {
        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Data di dihapus")
            .show()
        Handler().postDelayed({
            startActivity(Intent(this, SettingActivity::class.java))
            finish()
        }, 1000)
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun isLoading(state: Boolean) {
        btnDelete.isEnabled = !state
    }
}