package com.example.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wisataproject.contracts.LoginActivityContract
import com.example.wisataproject.presenters.LoginActivityPresenter
import com.example.wisataproject.utilities.WisataUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginActivityContract.View {
    private var presenter = LoginActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        isLoggedIn()
        presenter = LoginActivityPresenter(this)
        Login()
    }

    private fun Login(){
        btnLogin.setOnClickListener{
            val email = etId.text.toString().trim()
            val password = etPass.text.toString().trim()

            if(email.isNotEmpty() && password.isNotEmpty()){
                presenter.login(email, password)

            }else{
                toast("Isi semua form")
            }
        }
    }
    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun success(token: String, user_id: String) {
        WisataUtils.setToken(this, token, user_id)
        startActivity(Intent(this, DashboardActivity::class.java))
            .also{
                finish()
            }

    }

    override fun isLoading(state: Boolean) {
        btnLogin.isEnabled = !state
    }

    override fun isError(err: String?) {
        inId.error = err
    }

    override fun passwordError(err: String?) {
        inPass.error = err
    }

    override fun notConnect() {

    }

    private fun isLoggedIn(){
        val token = WisataUtils.getToken(this)
        if(token != null){
            startActivity(Intent(this, DashboardActivity::class.java)).also { finish() }
        }
    }
}