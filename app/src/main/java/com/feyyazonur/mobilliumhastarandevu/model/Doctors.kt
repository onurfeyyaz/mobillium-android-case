package com.feyyazonur.mobilliumhastarandevu.model

import com.google.gson.annotations.SerializedName

data class Doctors(
    @SerializedName("doctors")
    var doctors: List<Doctor>
)