package com.feyyazonur.mobilliumhastarandevu

import com.feyyazonur.mobilliumhastarandevu.model.DoctorsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Örnek istek: https://www.mobillium.com/android/doctors.json
private const val BASE_URL = "https://www.mobillium.com/"

interface RetrofitService {

    @GET("android/doctors.json")
    fun getAllDoctors(): Call<DoctorsResponse>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create((RetrofitService::class.java))
            }
            return retrofitService!!
        }
    }
}