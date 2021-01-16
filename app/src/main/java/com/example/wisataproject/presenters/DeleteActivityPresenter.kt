package com.example.wisataproject.presenters

import com.example.wisataproject.contracts.WisataActiviyContract
import com.example.wisataproject.models.Wisata
import com.example.wisataproject.utilities.WisataUtils
import com.example.wisataproject.webservices.WisataAPI
import com.example.wisataproject.webservices.WrappedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteActivityPresenter(v: WisataActiviyContract.ViewDelete) : WisataActiviyContract.InteractionDelete {
    private var view : WisataActiviyContract.ViewDelete? = v
    private var api = WisataAPI.instance()
    override fun delete(id: Int, token: String) {
        val request = api.deleteData(id, token)
        request.enqueue(object : Callback<WrappedResponse<Wisata>>{
            override fun onFailure(call: Call<WrappedResponse<Wisata>>, t: Throwable) {
                view?.toast("Koneksi tidak bisa")
            }

            override fun onResponse(
                call: Call<WrappedResponse<Wisata>>,
                response: Response<WrappedResponse<Wisata>>
            ) {
                if (response.isSuccessful){
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