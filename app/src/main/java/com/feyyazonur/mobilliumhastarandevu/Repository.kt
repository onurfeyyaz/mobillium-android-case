package com.feyyazonur.mobilliumhastarandevu

class Repository(private val retrofitService: RetrofitService) {

    fun getAllDoctors() = retrofitService.getAllDoctors()
}