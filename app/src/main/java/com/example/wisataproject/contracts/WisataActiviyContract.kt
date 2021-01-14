package com.example.wisataproject.contracts

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

    interface Interaction{
        fun allUser(token: String)
        fun destroy()
    }

    interface InteractionPost{
        fun validate(name: String, location: String, description: String,user_id: Int) : Boolean
        fun save(name: String, location: String, description: String, user_id: Int)
        fun destroy()
    }

}