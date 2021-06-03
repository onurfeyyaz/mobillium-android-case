package com.feyyazonur.mobilliumhastarandevu

import com.feyyazonur.mobilliumhastarandevu.model.Doctors
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("android/doctors.json")
    fun getAllDoctors(): Call<Doctors>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.mobillium.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create((RetrofitService::class.java))
            }
            return retrofitService!!
        }
    }
}