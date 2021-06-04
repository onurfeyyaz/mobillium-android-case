package com.feyyazonur.mobilliumhastarandevu.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doctor(
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("user_status")
    var userStatus: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("image")
    var image: Image
) : Parcelable