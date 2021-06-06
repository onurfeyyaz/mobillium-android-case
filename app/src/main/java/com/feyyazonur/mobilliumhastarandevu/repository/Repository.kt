package com.feyyazonur.mobilliumhastarandevu.repository

import com.feyyazonur.mobilliumhastarandevu.network.RetrofitService

class Repository(private val retrofitService: RetrofitService) {

    fun getAllDoctors() = retrofitService.getAllDoctors()
}