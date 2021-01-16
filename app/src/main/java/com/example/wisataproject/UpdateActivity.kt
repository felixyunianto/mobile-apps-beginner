package com.example.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.wisataproject.contracts.WisataActiviyContract
import com.example.wisataproject.presenters.UpdateActivityPresenter
import com.example.wisataproject.utilities.WisataUtils
import kotlinx.android.synthetic.main.activity_create_tourism.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_update.etDescription
import kotlinx.android.synthetic.main.activity_update.etLocation
import kotlinx.android.synthetic.main.activity_update.etName

class UpdateActivity : AppCompatActivity(), WisataActiviyContract.ViewEdit {
    private var presenter = UpdateActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        etIdTourism.setText(intent.getStringExtra("ID_USER"))
        etName.setText(intent.getStringExtra("NAME"))
        etLocation.setText(intent.getStringExtra("LOCATION"))
        etDescription.setText(intent.getStringExtra("DESCRIPTION"))
        presenter = UpdateActivityPresenter(this)
        Update()
    }

    private fun Update(){
        btnUpdate.setOnClickListener{
            val token = WisataUtils.getToken(this)
            val id = etIdTourism.text.toString().toInt();
            val name = etName.text.toString().trim()
            val location = etLocation.text.toString().trim()
            val description = etDescription.text.toString().trim();

            if(name.isNotEmpty() && location.isNotEmpty() && description.isNotEmpty()){
                token?.let { it1 -> presenter.update(id, it1, name, location, description) }
            }else{
                toast("Isi semua form")
            }
        }
    }

    override fun success(message: String?) {
        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Data di ubah")
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
        btnUpdate.isEnabled = !state
    }


}