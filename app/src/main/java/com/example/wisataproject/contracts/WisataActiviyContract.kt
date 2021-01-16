package com.example.wisataproject.contracts

import android.text.Editable
import com.example.wisataproject.models.Wisata

interface WisataActiviyContract {
    interface View{
        fun attachToRecycle(tourism: List<Wisata>)
        fun toast(message : String?)
        fun isLoading(state : Boolean)
        fun notConnect(message: String?)
    }

    interface ViewCreate{
        fun success(message: String?)
        fun toast(message : String?)
        fun isLoading(state : Boolean)
    }

    interface ViewEdit{
        fun success(message: String?)
        fun toast(message: String?)
        fun isLoading(state: Boolean)
    }

    interface ViewDelete{
        fun success(message: String?)
        fun toast(message: String?)
        fun isLoading(state: Boolean)
    }

    interface Interaction{
        fun allUser(token: String)
        fun destroy()
    }

    interface InteractionPost{
        fun validate(name: String, location: String, description: String) : Boolean
        fun save(token:String, name: String, location: String, description: String)
        fun destroy()
    }

    interface InteractionUpdate {
        fun validate(name: String, location: String, description: String) : Boolean
        fun update(id: Int, token: String, name: String, location: String, description: String)
        fun destroy()
    }

    interface InteractionDelete{
        fun delete(id: Int, token: String)
        fun destroy()
    }

}