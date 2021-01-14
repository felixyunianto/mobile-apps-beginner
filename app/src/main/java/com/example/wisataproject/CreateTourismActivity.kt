package com.example.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowAnimationFrameStats
import android.widget.Toast
import com.example.wisataproject.contracts.WisataActiviyContract
import com.example.wisataproject.presenters.CreateActivityPresenter
import com.example.wisataproject.utilities.WisataUtils
import kotlinx.android.synthetic.main.activity_create_tourism.*

class CreateTourismActivity : AppCompatActivity(), WisataActiviyContract.ViewCreate {
    private var presenter = CreateActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_tourism)
        presenter = CreateActivityPresenter(this)
        Save()
    }

    private fun Save(){
        btnSave.setOnClickListener{
            val token = WisataUtils.getToken(this)
            val name = etName.text.toString().trim()
            val location  = etLocation.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if(name.isNotEmpty() && location.isNotEmpty() && description.isNotEmpty()){
                token?.let { it1 -> presenter.save(it1, name, location, description) }
            }else{
                toast("Isi Semua form")
            }
        }
    }


    override fun success(message: String?) {
        startActivity(Intent(this, MainActivity::class.java))
            .also { finish() }
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun isLoading(state: Boolean) {
        btnSave.isEnabled = !state
    }
}