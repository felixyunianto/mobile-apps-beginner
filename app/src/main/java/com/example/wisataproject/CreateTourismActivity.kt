package com.example.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        getUserId()
        Save()
    }

    private fun Save(){
        btnSave.setOnClickListener{
            val name = etName.text.toString().trim()
            val location  = etLocation.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val user_id = etUser.text?.toString().trim()

            if(name.isNotEmpty() && location.isNotEmpty() && description.isNotEmpty()){
                presenter.save(name, location, description,user_id)
            }else{
                toast("Isi Semua form")
            }
        }
    }

    private fun getUserId(){
        val user_id = WisataUtils.getUserId(this);
        etUser.setText(user_id)
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