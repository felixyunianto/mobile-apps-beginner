package com.example.wisataproject.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wisata(
    @SerializedName("id") var id : Int? = null,
    @SerializedName("name") var name : String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("description") var description : String? = null,
    @SerializedName("user_id") var user_id : String? = null
): Parcelable