package com.feyyazonur.mobilliumhastarandevu.model

import com.google.gson.annotations.SerializedName

data class DoctorsResponse(
    @SerializedName("doctors")
    var doctors: ArrayList<Doctor>
)