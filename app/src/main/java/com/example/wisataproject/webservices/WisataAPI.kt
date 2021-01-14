package com.example.wisataproject.webservices

import com.example.wisataproject.models.User
import com.example.wisataproject.models.Wisata
import com.google.gson.annotations.SerializedName
import com.example.wisataproject.utilities.WisataUtils
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.io.FileDescriptor
import java.util.concurrent.TimeUnit

class WisataAPI {
    companion object{
        private var retrofit: Retrofit? = null

        private var okHttpClient = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()

        fun instance(): WisataAPIService = getClient().create(WisataAPIService::class.java)

        private fun getClient(): Retrofit {
            return if(retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(WisataUtils.API_ENDPOINT).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }
    }
}

interface WisataAPIService {
    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") email: String? = null,
        @Field("password") password: String? = null
    ): Call<WrappedResponse<User>>

    @GET("tourism")
    fun get(@Header("x-access-token") token : String) : Call<WrappedListResponse<Wisata>>

    @FormUrlEncoded
    @POST("tourism")
    fun save(
        @Header("x-access-token") token: String,
        @Field("name") name: String? = null,
        @Field("location") location: String? = null,
        @Field("description") description: String? = null,
        @Field("user_id") user_id : Int? = null
    ): Call<WrappedResponse<Wisata>>
}

data class WrappedResponse<T>(
    @SerializedName("message") var message: String?,
    @SerializedName("status") var status: Boolean,
    @SerializedName("data") var data : T?
){
    constructor() : this(null, false, null)
}

data class WrappedListResponse<T>(
    @SerializedName("message") var message: String?,
    @SerializedName("status") var status: Boolean,
    @SerializedName("data") var data : List<T>
){
    constructor() : this(null, false, listOf())
}