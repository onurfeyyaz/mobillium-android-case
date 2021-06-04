package com.feyyazonur.mobilliumhastarandevu.model

import com.google.gson.annotations.SerializedName

class DoctorsResponse(
    @SerializedName("doctors")
    var doctors: ArrayList<Doctor>
)