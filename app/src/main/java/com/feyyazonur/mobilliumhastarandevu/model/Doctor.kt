package com.feyyazonur.mobilliumhastarandevu.model

import com.google.gson.annotations.SerializedName

class Doctor(
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("user_status")
    var userStatus: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("image")
    var image: Image
)