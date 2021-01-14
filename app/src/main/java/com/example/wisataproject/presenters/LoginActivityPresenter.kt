package com.example.wisataproject.presenters

import com.example.wisataproject.contracts.LoginActivityContract
import com.example.wisataproject.models.User
import com.example.wisataproject.utilities.WisataUtils
import com.example.wisataproject.webservices.WisataAPI
import com.example.wisataproject.webservices.WrappedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityPresenter (v : LoginActivityContract.View?) : LoginActivityContract.Interaction{
    private var view: LoginActivityContract.View? = v
    private var api = WisataAPI.instance()

    override fun validate(id: String, password: String): Boolean {
        view?. passwordError(null)
        if(!WisataUtils.isValidPassword(password)){
            view?.passwordError("Password tidak valid")
            return false
        }

        return true
    }

    override fun login(email: String, password: String) {
        view?.isLoading(true)
        api.login(email, password).enqueue(object: Callback<WrappedResponse<User>>{
            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.toast("Koneksi tidak bisa")
                view?.notConnect()
            }

            override fun onResponse(
                call: Call<WrappedResponse<User>>,
                response: Response<WrappedResponse<User>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null && body.status){
                        view?.toast("Selamat datang ${body.data!!.name}")
                        println("body "+ body.data)
                        val token = body.data?.token!!
                        val id_user = body.data?.id_user!!
                        view?.success(token, id_user)
                    }
                }else{
                    view?.toast("Ada yang tidak beres, coba lagi nanti, atau hubungi admin")
                }
                view?.isLoading(false)
            }
        })
    }

    override fun destroy() {
        view = null
    }
}

