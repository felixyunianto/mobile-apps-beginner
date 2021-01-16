package com.example.wisataproject.presenters

import com.example.wisataproject.contracts.WisataActiviyContract
import com.example.wisataproject.models.Wisata
import com.example.wisataproject.webservices.WisataAPI
import com.example.wisataproject.webservices.WrappedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivityPresenter(v: WisataActiviyContract.ViewEdit) : WisataActiviyContract.InteractionUpdate {
    private var view : WisataActiviyContract.ViewEdit? = v
    private var api = WisataAPI.instance()
    override fun validate(name: String, location: String, description: String): Boolean {
        return true
    }

    override fun update(id: Int, token: String, name: String, location: String, description: String) {
        val requestUpdate = api.updateData(id,token, name, location, description)
        requestUpdate.enqueue(object :  Callback<WrappedResponse<Wisata>> {
            override fun onFailure(call: Call<WrappedResponse<Wisata>>, t: Throwable) {
                view?.toast("Koneksi tidak bisa")
            }

            override fun onResponse(
                call: Call<WrappedResponse<Wisata>>,
                response: Response<WrappedResponse<Wisata>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null && body.status){
                        view?.success("Berhasil")
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